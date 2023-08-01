package sg.edu.rp.c346.id22002222.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner spnRating;
    EditText etTitle, etYear, etGenre;

    Button btnInsert, btnShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTitle = findViewById(R.id.editTextTitle);
        etYear = findViewById(R.id.editTextYear);
        etGenre = findViewById(R.id.editTextGenre);
        spnRating = findViewById(R.id.spinner);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);


        spnRating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedRating = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when nothing is selected
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                String title = etTitle.getText().toString();
                String genre = etGenre.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                String selectedRating = spnRating.getSelectedItem().toString();
                Log.i("Rating", selectedRating);

                db.insertMovies(title,genre,year,selectedRating);
                Toast.makeText(MainActivity.this, "Movies successfully added", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, EditMovieActivity.class);

//                intent.putExtra("itemsPos",  spnRating.getSelectedItemPosition());
//                Log.i("Rating", String.valueOf(spnRating.getSelectedItemPosition()));


            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, moviedetailsActivity.class);
                startActivity(intent);


            }
        });


    }




}
