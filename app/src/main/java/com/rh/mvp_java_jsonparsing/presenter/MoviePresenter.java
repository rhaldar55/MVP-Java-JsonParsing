package com.rh.mvp_java_jsonparsing.presenter;

import com.rh.mvp_java_jsonparsing.contract.MovieListContract;
import com.rh.mvp_java_jsonparsing.model.Movie;
import com.rh.mvp_java_jsonparsing.service.MovieListModel;

import java.util.List;

public class MoviePresenter implements MovieListContract.Presenter, MovieListContract.Model.OnFinishListener{

    private MovieListContract.View movieListView;
    private MovieListContract.Model movieListModel;

    public MoviePresenter(MovieListContract.View movieListView) {
        this.movieListView = movieListView;
        this.movieListModel = new MovieListModel();
    }

    //MovieListContract.Presenter
    @Override
    public void onDestroy() {
        this.movieListView = null;

    }

    @Override
    public void getMoreData(int pageNumber) {
        if (movieListView != null){
            movieListView.showProgress();
        }

        movieListModel.getMovieList(this, pageNumber);
    }

    @Override
    public void requestDataFromServer() {
        if (movieListView !=null){
            movieListView.showProgress();
        }
        movieListModel.getMovieList(this, 1);
    }

    //MovieListContract.Model.OnFinishListener
    @Override
    public void onFinished(List<Movie> movieList) {
        movieListView.setDataRecyclerView(movieList);
        if (movieListView!=null){
            movieListView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        movieListView.onResponseFailure(throwable);
        if (movieListView!=null){
            movieListView.hideProgress();
        }
    }
}
