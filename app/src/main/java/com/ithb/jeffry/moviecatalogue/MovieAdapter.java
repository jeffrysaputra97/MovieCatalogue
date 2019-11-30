package com.ithb.jeffry.moviecatalogue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {
    private final Context context;
    private ArrayList<Movie> movies = new ArrayList<>();

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(itemView);
        Movie movie = (Movie) getItem(i);
        viewHolder.bind(movie);

        return itemView;
    }

    private class ViewHolder {
        private ImageView imgPhoto;
        private TextView txtTitle;
        private TextView txtDescription;

        ViewHolder(View view) {
            imgPhoto = view.findViewById(R.id.img_photo);
            txtTitle = view.findViewById(R.id.txt_title);
            txtDescription = view.findViewById(R.id.txt_description);
        }

        void bind(Movie movie) {
            imgPhoto.setImageResource(movie.getPhoto());
            txtTitle.setText(movie.getTitle());
            txtDescription.setText(movie.getDescription());
        }
    }
}
