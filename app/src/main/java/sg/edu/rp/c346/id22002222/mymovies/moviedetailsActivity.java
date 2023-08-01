package sg.edu.rp.c346.id22002222.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class moviedetailsActivity extends AppCompatActivity {

    ArrayList<Movies> al;
    CustomAdapter ca;
    ListView lisv;
    Button btnfilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetails);

        lisv = findViewById(R.id.lisvDetails);
        btnfilter = findViewById(R.id.btnFilter);

        DBHelper db = new DBHelper(moviedetailsActivity.this);

        al= db.getMovies();

        ca = new CustomAdapter(this, R.layout.row, al);
        lisv.setAdapter(ca);
        ca.notifyDataSetChanged();

        lisv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Movies moviesdata = al.get(position);
                Intent i = new Intent(moviedetailsActivity.this,
                        EditMovieActivity.class);
                i.putExtra("movies", moviesdata);

                startActivity(i);

            }
        });

        btnfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        DBHelper dbh = new DBHelper(moviedetailsActivity.this);
                        al.clear();
                        al.addAll(dbh.getAllPG13("PG13"));
                        ca.notifyDataSetChanged();

            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(this);
        al.clear();
        al.addAll(dbh.getMovies());
        ca.notifyDataSetChanged();


    }
}