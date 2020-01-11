package com.ithb.jeffry.moviecatalogue.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.ithb.jeffry.moviecatalogue.model.Movie;
import com.ithb.jeffry.moviecatalogue.model.StateLiveData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TvViewModel extends ViewModel {
    private static final String API_KEY = "c7eaf414fbb4ed5476b0c9a1e4258242";
    private StateLiveData<ArrayList<Movie>> listTvs = new StateLiveData<>();

    public void setListTvs() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> tvItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/tv?api_key=" + API_KEY +"&language=en-US";
        Log.d("setListTvsURL", url);
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray results = responseObject.getJSONArray("results");

                    for (int i = 0; i < results.length(); i++) {
                        JSONObject tv = results.getJSONObject(i);
                        Movie tvItem = new Movie();
                        tvItem.setPhoto("https://image.tmdb.org/t/p/w500" + tv.getString("poster_path"));
                        tvItem.setTitle(tv.getString("original_name"));
                        tvItem.setDescription(tv.getString("overview"));
                        tvItems.add(tvItem);
                    }

                    listTvs.postSuccess(tvItems);
                } catch (Exception e) {
                    listTvs.postComplete();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                listTvs.postError(error);
            }
        });
    }

    public StateLiveData<ArrayList<Movie>> getListTvs() {
        return listTvs;
    }
}
