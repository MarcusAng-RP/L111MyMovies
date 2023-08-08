package sg.edu.rp.c346.id22002222.mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {



    private static final int DATABASE_VER = 1;
    // Filename of the database
    private static final String DATABASE_NAME = "movies.db";

    private static final String TABLE_MOVIES = "movies";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE= "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATING = "rating";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_MOVIES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_GENRE+ " TEXT,"
                + COLUMN_YEAR+ " INTEGER,"
                + COLUMN_RATING + " TEXT )";
        db.execSQL(createTableSql);
        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertMovies(String title, String genre, int year, String rating){
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, title);

        values.put(COLUMN_GENRE, genre);

        values.put(COLUMN_YEAR, year);

        values.put(COLUMN_RATING, rating);

        db.insert(TABLE_MOVIES, null, values);

        db.close();
    }

    public ArrayList<Movies> getMovies() {
        ArrayList<Movies> movies = new ArrayList<Movies>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR,COLUMN_RATING};
        Cursor cursor = db.query(TABLE_MOVIES, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id  = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);

                Movies obj = new Movies(id, title, genre, year, rating);
                movies.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movies;
    }

    public int updateMovies(Movies movies){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, movies.getTitle());
        values.put(COLUMN_GENRE, movies.getGenre());
        values.put(COLUMN_YEAR, movies.getYear());
        values.put(COLUMN_RATING, movies.getRating());
        String condition = COLUMN_ID + "= ?" ;
        String[] args = {String.valueOf(movies.getId())};
        int result = db.update(TABLE_MOVIES, values, condition, args);
        db.close();
        return result;
    }

    public int deleteMovies(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_MOVIES, condition, args);
        db.close();
        return result;
    }

    public ArrayList<Movies> getAllPG13(String keyword) {
        ArrayList<Movies> Movies = new ArrayList<Movies>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE,COLUMN_YEAR,COLUMN_RATING};
        String condition = COLUMN_RATING + " Like ?";
//        String[] args = { "%" +  keyword + "%"};
        String[] args = { "PG13"};
        Cursor cursor = db.query(TABLE_MOVIES, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title= cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movies movies = new Movies(id, title,genre, year, rating);
                Movies.add(movies);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Movies;
    }
    }

