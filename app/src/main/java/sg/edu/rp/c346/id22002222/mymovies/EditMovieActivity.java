package sg.edu.rp.c346.id22002222.mymovies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.amrdeveloper.lottiedialog.LottieDialog;

public class EditMovieActivity extends AppCompatActivity {

    Spinner spnRating;
    EditText etTitle, etGenre, etYear;
    TextView tvId;
    Button btnUpdate, btnDelete, btnCancel;
    LottieDialog dialog1;
    LottieDialog dialog2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        etTitle = findViewById(R.id.editTextTitle);
        etGenre = findViewById(R.id.editTextGenre);
        etYear = findViewById(R.id.editTextYear);
        tvId = findViewById(R.id.tvId);
        spnRating = findViewById(R.id.spinner);

        btnUpdate = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnShowList);
        btnCancel =findViewById(R.id.btnCancel);

        Intent i = getIntent();
        Movies movies = (Movies) i.getSerializableExtra("movies");


        tvId.setText(String.valueOf(movies.getId()));
        etTitle.setText(movies.getTitle());
        etGenre.setText(movies.getGenre());
        etYear.setText(String.valueOf(movies.getYear()));
//        spnRating.getSelectedItem();
//        Intent intentReceived = getIntent();
//        int position = i.getIntExtra("itemsPos", 0);

//        spnRating.setSelection(position);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.ratingItems, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRating.setAdapter(adapter);


        int selectedPosition = adapter.getPosition(movies.getRating());
        spnRating.setSelection(selectedPosition);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditMovieActivity.this);
                movies.setTitle(etTitle.getText().toString());
                movies.setGenre(etGenre.getText().toString());
                movies.setYear(Integer.parseInt(etYear.getText().toString()));
                movies.setRating(spnRating.getSelectedItem().toString());

                dbh.updateMovies(movies);

                finish();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditMovieActivity.this);
//                myBuilder.setTitle("Danger");
//                myBuilder.setMessage("Are you sure you want to discard the changes");
//                myBuilder.setCancelable(false);
//
//
//
//                myBuilder.setPositiveButton("DO NOT DISCARD", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//                myBuilder.setNegativeButton("DISCARD", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
////                        tvDemo2.setText("You have selected negative");
//                    }
//                });
//                AlertDialog myDialog = myBuilder.create();
//                myDialog.show();

                Button okButton = new Button(EditMovieActivity.this);
                okButton.setText("DISCARD");
                okButton.setOnClickListener(view -> {
                    dialog1.cancel();
                    finish();

                });

                Button cancelButton = new Button(EditMovieActivity.this);
                cancelButton.setText("DO NOT DISCARD");
                cancelButton.setOnClickListener(view -> {
                    dialog1.cancel();

                });

                dialog1 = new LottieDialog(EditMovieActivity.this)
                        .setAnimation(R.raw.animation_cancel)
                        .setAnimationRepeatCount(LottieDialog.INFINITE)
                        .setAutoPlayAnimation(true)
                        .setTitle("DANGER")
                        .setTitleColor(Color.RED)
                        .setMessage("Are you sure you want to discard changes")
                        .setMessageColor(Color.BLACK)
                        .setDialogBackground(Color.WHITE)
                        .setCancelable(false)
                        .addActionButton(okButton)
                        .addActionButton(cancelButton)
                        .setOnShowListener(dialogInterface -> {})
                        .setOnDismissListener(dialogInterface -> {})
                        .setOnCancelListener(dialogInterface -> {});
                dialog1.show();


            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditMovieActivity.this);
//                myBuilder.setTitle("Danger");
//                myBuilder.setMessage("Are you sure you want to delete the movie " + movies.getTitle() );
//                myBuilder.setCancelable(false);
//
//
//
//                myBuilder.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//                myBuilder.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        DBHelper dbh = new DBHelper(EditMovieActivity.this);
//                        dbh.deleteSong(movies.getId());
//                        finish();
////
//                    }
//                });
//
//                AlertDialog myDialog = myBuilder.create();
//                myDialog.show();

                Button okButton = new Button(EditMovieActivity.this);
                okButton.setText("DELETE");
                okButton.setOnClickListener(view -> {
                    DBHelper dbh = new DBHelper(EditMovieActivity.this);
                        dbh.deleteMovies(movies.getId());
                        dialog2.cancel();
                        finish();

                });

                Button cancelButton = new Button(EditMovieActivity.this);
                cancelButton.setText("CANCEL");
                cancelButton.setOnClickListener(view -> {
                        dialog2.cancel();

                });

                 dialog2 = new LottieDialog(EditMovieActivity.this)
                        .setAnimation(R.raw.animation)
                        .setAnimationRepeatCount(LottieDialog.INFINITE)
                        .setAutoPlayAnimation(true)
                        .setTitle("DANGER")
                        .setTitleColor(Color.RED)
                        .setMessage("Are you sure you want to delete movie " + movies.getTitle())
                        .setMessageColor(Color.BLACK)
                        .setDialogBackground(Color.WHITE)
                        .setCancelable(true)
                        .addActionButton(okButton)
                        .addActionButton(cancelButton)

                        .setOnShowListener(dialogInterface -> {})
                        .setOnDismissListener(dialogInterface -> {})
                        .setOnCancelListener(dialogInterface -> {});
                dialog2.show();
            }
        });


    }
}
