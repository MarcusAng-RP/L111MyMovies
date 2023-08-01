package sg.edu.rp.c346.id22002222.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EditMovieActivity extends AppCompatActivity {

    Spinner spnRating;
    EditText etTitle, etGenre, etYear;
    Button btnUpdate, btnDelete, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        etTitle = findViewById(R.id.editTextTitle);
        etGenre = findViewById(R.id.editTextGenre);
        etYear = findViewById(R.id.editTextYear);
        spnRating = findViewById(R.id.spinner);

        btnUpdate = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnShowList);
        btnCancel =findViewById(R.id.btnCancel);

        Intent i = getIntent();
        Movies movies = (Movies) i.getSerializableExtra("movies");
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
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditMovieActivity.this);
                dbh.deleteSong(movies.getId());
                finish();
            }
        });
    }
}
