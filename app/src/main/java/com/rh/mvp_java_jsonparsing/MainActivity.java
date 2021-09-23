package com.rh.mvp_java_jsonparsing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rh.mvp_java_jsonparsing.contract.MovieListContract;
import com.rh.mvp_java_jsonparsing.model.Movie;
import com.rh.mvp_java_jsonparsing.presenter.MoviePresenter;
import com.rh.mvp_java_jsonparsing.service.MovieListModel;
import com.rh.mvp_java_jsonparsing.view.MovieListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieListContract.View {

    private MoviePresenter moviePresenter;
    private RecyclerView recyclerView;
    private List<Movie> movieList;
    private MovieListAdapter movieListAdapter;
    private ProgressBar progressBar;
    private int pageNumber = 1;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvMovieList);
        progressBar = findViewById(R.id.pbLoading);
        movieList = new  ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        moviePresenter = new MoviePresenter(this);
        moviePresenter.requestDataFromServer();
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDataRecyclerView(List<Movie> movieList) {
        movieList.addAll(movieList);
        movieListAdapter = new MovieListAdapter(movieList, this);
        recyclerView.setAdapter(movieListAdapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e("ERROR:", throwable.getMessage());
        Toast.makeText(MainActivity.this, "Error in getting data", Toast.LENGTH_LONG).show();
    }
}