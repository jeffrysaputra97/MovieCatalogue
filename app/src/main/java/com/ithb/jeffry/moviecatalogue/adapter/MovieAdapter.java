package com.ithb.jeffry.moviecatalogue.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ithb.jeffry.moviecatalogue.R;
import com.ithb.jeffry.moviecatalogue.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private OnItemClickCallback onItemClickCallback;
    private ArrayList<Movie> movies = new ArrayList<>();

    public void setData(ArrayList<Movie> items) {
        movies.clear();
        movies.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPhoto;
        private TextView txtTitle;
        private TextView txtDescription;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtDescription = itemView.findViewById(R.id.txt_description);
        }

        void bind(final Movie movie) {
            Glide.with(itemView.getContext())
                    .load(movie.getPhoto())
                    .apply(new RequestOptions().override(100, 150))
                    .into(imgPhoto);

            txtTitle.setText(movie.getTitle());
            txtDescription.setText(movie.getDescription());
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    onItemClickCallback.onItemClicked(movie);
                }
            });
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Movie data);
    }
}
