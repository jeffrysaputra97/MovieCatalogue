package com.ithb.jeffry.moviecatalogue.fragment;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ithb.jeffry.moviecatalogue.R;
import com.ithb.jeffry.moviecatalogue.activity.DetailActivity;
import com.ithb.jeffry.moviecatalogue.adapter.MovieAdapter;
import com.ithb.jeffry.moviecatalogue.model.Movie;

import java.util.ArrayList;

public class MovieFragment extends Fragment{
    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView rvMovies;
    private ArrayList<Movie> movies = new ArrayList<>();

    public MovieFragment() {
        // Required empty public constructor
    }

    public static MovieFragment newInstance(int index) {
        MovieFragment fragment = new MovieFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvMovies  = view.findViewById(R.id.rv_list);
        rvMovies.setHasFixedSize(true);

        int section = 1;

        if (getArguments() != null) {
            section = getArguments().getInt(ARG_SECTION_NUMBER);
        }

        movies.addAll(getMovies(section));
        showRecyclerList();
    }

    private ArrayList<Movie> getMovies(int section) {
        TypedArray dataPhoto;
        String[] dataTitle, dataYear, dataRelease, dataLanguage, dataRuntime, dataGenre, dataDescription;
        ArrayList<Movie> listMovie = new ArrayList<>();

        if (section == 2) {
            dataTitle = getResources().getStringArray(R.array.data_tv_title);
            dataPhoto = getResources().obtainTypedArray(R.array.data_tv_photo);
            dataYear = getResources().getStringArray(R.array.data_tv_year);
            dataRelease = getResources().getStringArray(R.array.data_tv_release);
            dataLanguage = getResources().getStringArray(R.array.data_tv_language);
            dataRuntime = getResources().getStringArray(R.array.data_tv_runtime);
            dataGenre = getResources().getStringArray(R.array.data_tv_genres);
            dataDescription = getResources().getStringArray(R.array.data_tv_description);
        } else {
            dataTitle = getResources().getStringArray(R.array.data_movie_title);
            dataPhoto = getResources().obtainTypedArray(R.array.data_movie_photo);
            dataYear = getResources().getStringArray(R.array.data_movie_year);
            dataRelease = getResources().getStringArray(R.array.data_movie_release);
            dataLanguage = getResources().getStringArray(R.array.data_movie_language);
            dataRuntime = getResources().getStringArray(R.array.data_movie_runtime);
            dataGenre = getResources().getStringArray(R.array.data_movie_genres);
            dataDescription = getResources().getStringArray(R.array.data_movie_description);
        }

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

        dataPhoto.recycle();
        return listMovie;
    }

    private void showRecyclerList() {
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        MovieAdapter movieAdapter = new MovieAdapter(movies);
        rvMovies.setAdapter(movieAdapter);

        movieAdapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                showSelectedMovie(data);
            }
        });
    }

    private void showSelectedMovie(Movie movie) {
        Intent movieDetailIntent = new Intent(getView().getContext(), DetailActivity.class);
        movieDetailIntent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(movieDetailIntent);
    }
}
