package com.ithb.jeffry.moviecatalogue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setActionBarTitle(getString(R.string.detail_title));

        ImageView imgMoviePhoto = findViewById(R.id.detail_img_item_photo);
        TextView tvMovieTitle = findViewById(R.id.detail_tv_movie_title);
        TextView tvMovieYear = findViewById(R.id.detail_tv_movie_year);
        TextView tvMovieRelease = findViewById(R.id.detail_tv_release_content);
        TextView tvMovieLanguage = findViewById(R.id.detail_tv_language_content);
        TextView tvMovieRuntime = findViewById(R.id.detail_tv_runtime_content);
        TextView tvMovieGenre = findViewById(R.id.detail_tv_genre);
        TextView tvMovieOverview = findViewById(R.id.detail_tv_overview);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        if (movie != null) {
            imgMoviePhoto.setImageResource(movie.getPhoto());
            tvMovieTitle.setText(movie.getTitle());
            tvMovieYear.setText(movie.getYear());
            tvMovieRelease.setText(movie.getReleaseDate());
            tvMovieLanguage.setText(movie.getLanguage());
            tvMovieRuntime.setText(movie.getRuntime());
            tvMovieGenre.setText(movie.getGenre());
            tvMovieOverview.setText(movie.getDescription());
        }
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
