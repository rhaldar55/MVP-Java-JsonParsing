package com.rh.mvp_java_jsonparsing.contract;

import com.rh.mvp_java_jsonparsing.model.Movie;

import java.util.List;

public interface MovieListContract {

    interface  Model{

        interface OnFinishListener{
            void onFinished(List<Movie> movieList);
            void onFailure(Throwable throwable);
        }

        void getMovieList(OnFinishListener onFinishListener, int pageNumber);

    }


    interface View{
        void showProgress();
        void hideProgress();
        void setDataRecyclerView(List<Movie> movieList);
        void onResponseFailure(Throwable throwable);
    }

    interface Presenter{
        void onDestroy();
        void getMoreData(int pageNumber);
        void requestDataFromServer();
    }
}
