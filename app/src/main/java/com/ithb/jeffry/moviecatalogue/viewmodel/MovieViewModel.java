package com.ithb.jeffry.moviecatalogue.viewmodel;

import androidx.lifecycle.ViewModel;

import com.ithb.jeffry.moviecatalogue.model.Movie;
import com.ithb.jeffry.moviecatalogue.model.StateLiveData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieViewModel extends ViewModel {
    private static final String API_KEY = "c7eaf414fbb4ed5476b0c9a1e4258242";
    private StateLiveData<ArrayList<Movie>> listMovies = new StateLiveData<>();

    public void setListMovies() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> movieItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY +"&language=en-US";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray results = responseObject.getJSONArray("results");

                    for (int i = 0; i < results.length(); i++) {
                        JSONObject movie = results.getJSONObject(i);
                        Movie movieItem = new Movie();
                        movieItem.setPhoto("https://image.tmdb.org/t/p/w500" + movie.getString("poster_path"));
                        movieItem.setTitle(movie.getString("title"));
                        movieItem.setDescription(movie.getString("overview"));
                        movieItems.add(movieItem);
                    }

                    listMovies.postSuccess(movieItems);
                } catch (Exception e) {
                    listMovies.postComplete();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                listMovies.postError(error);
            }
        });
    }

    public StateLiveData<ArrayList<Movie>> getListMovies() {
        return listMovies;
    }
}
