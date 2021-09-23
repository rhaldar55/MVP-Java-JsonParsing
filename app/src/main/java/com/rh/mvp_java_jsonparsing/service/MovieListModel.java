package com.rh.mvp_java_jsonparsing.service;

import android.util.Log;

import com.rh.mvp_java_jsonparsing.contract.MovieListContract;
import com.rh.mvp_java_jsonparsing.model.Movie;
import com.rh.mvp_java_jsonparsing.model.MovieListResponse;
import com.rh.mvp_java_jsonparsing.network.ApiClient;
import com.rh.mvp_java_jsonparsing.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListModel implements MovieListContract.Model {

    private final String TAG = "MovieListModel";
    private int pageNumber = 1;

    @Override
    public void getMovieList(OnFinishListener onFinishListener, int pageNumber) {

        ApiInterface apiservice = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieListResponse> call = apiservice.getPopularMovies(ApiClient.API_KEY, pageNumber);
        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                if (response.isSuccessful()){
                    List<Movie> movies = response.body().getResults();
                    Log.i(TAG, "Number of movies received : "+ movies.size());
                    onFinishListener.onFinished(movies);
                }
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                Log.i(TAG, t.toString());
                onFinishListener.onFailure(t);
            }
        });
    }


}
