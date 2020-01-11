package com.ithb.jeffry.moviecatalogue.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ithb.jeffry.moviecatalogue.R;
import com.ithb.jeffry.moviecatalogue.activity.DetailActivity;
import com.ithb.jeffry.moviecatalogue.adapter.MovieAdapter;
import com.ithb.jeffry.moviecatalogue.model.Movie;
import com.ithb.jeffry.moviecatalogue.model.StateData;
import com.ithb.jeffry.moviecatalogue.viewmodel.TvViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class TvFragment extends Fragment {
    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;
    private ConstraintLayout errorLayout;
    private ImageView errorImage;
    private TextView errorMessage;

    public TvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        errorLayout = view.findViewById(R.id.error_layout);
        errorImage = view.findViewById(R.id.error_image);
        errorMessage = view.findViewById(R.id.error_message);

        progressBar = view.findViewById(R.id.tv_progressBar);

        RecyclerView rvTv = view.findViewById(R.id.rv_tv);
        rvTv.setHasFixedSize(true);
        rvTv.setLayoutManager(new LinearLayoutManager(getActivity()));
        movieAdapter = new MovieAdapter();
        movieAdapter.notifyDataSetChanged();
        rvTv.setAdapter(movieAdapter);
        TvViewModel tvViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TvViewModel.class);

        loadData(tvViewModel);
    }

    private void loadData(TvViewModel tvViewModel) {
        tvViewModel.setListTvs();
        showLoading(true);

        tvViewModel.getListTvs().observe(getViewLifecycleOwner(), new Observer<StateData<ArrayList<Movie>>>() {
            @Override
            public void onChanged(StateData<ArrayList<Movie>> tvList) {
                switch (tvList.getStatus()) {
                    case SUCCESS:
                        movieAdapter.setData(tvList.getData());

                        movieAdapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
                            @Override
                            public void onItemClicked(Movie data) {
                                showSelectedTv(data);
                            }
                        });

                        showLoading(false);
                        break;
                    case ERROR:
                        showLoading(false);
                        showErrorMessage(R.drawable.error_connection, getString(R.string.error_connection_message));
                        break;
                    case COMPLETE:
                        showLoading(false);
                        showErrorMessage(R.drawable.error_result, getString(R.string.error_result_message));
                        break;
                }
            }
        });
    }

    private void showSelectedTv(Movie movie) {
        Intent movieDetailIntent = new Intent(Objects.requireNonNull(getView()).getContext(), DetailActivity.class);
        movieDetailIntent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(movieDetailIntent);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void showErrorMessage(int imageView, String message) {
        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
        }

        errorImage.setImageResource(imageView);
        errorMessage.setText(message);
    }
}
