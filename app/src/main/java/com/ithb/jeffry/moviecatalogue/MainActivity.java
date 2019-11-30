package com.ithb.jeffry.moviecatalogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MovieAdapter adapter;
    private TypedArray dataPhoto;
    private String[] dataTitle;
    private String[] dataYear;
    private String[] dataRelease;
    private String[] dataLanguage;
    private String[] dataRuntime;
    private String[] dataGenre;
    private String[] dataDescription;
    private ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBarTitle("Movie List");

        ListView listView = findViewById(R.id.lv_list);
        adapter = new MovieAdapter(this);
        listView.setAdapter(adapter);

        prepare();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent movieDetailIntent = new Intent(MainActivity.this, DetailActivity.class);
                movieDetailIntent.putExtra(DetailActivity.EXTRA_MOVIE, movies.get(i));
                startActivity(movieDetailIntent);
            }
        });
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void prepare() {
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
        dataTitle = getResources().getStringArray(R.array.data_title);
        dataYear = getResources().getStringArray(R.array.data_year);
        dataRelease = getResources().getStringArray(R.array.data_release);
        dataLanguage = getResources().getStringArray(R.array.data_language);
        dataRuntime = getResources().getStringArray(R.array.data_runtime);
        dataGenre = getResources().getStringArray(R.array.data_genres);
        dataDescription = getResources().getStringArray(R.array.data_description);
    }

    private void addItem() {
        movies = new ArrayList<>();

        for (int i = 0; i < dataTitle.length; i++) {
            Movie movie = new Movie();
            movie.setPhoto(dataPhoto.getResourceId(i, -1));
            movie.setTitle(dataTitle[i]);
            movie.setYear(dataYear[i]);
            movie.setReleaseDate(dataRelease[i]);
            movie.setLanguage(dataLanguage[i]);
            movie.setRuntime(dataRuntime[i]);
            movie.setGenre(dataGenre[i]);
            movie.setDescription(dataDescription[i]);

            movies.add(movie);
        }

        adapter.setMovies(movies);
    }
}
