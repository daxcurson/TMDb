package com.elad.themovie.service.repository.network.paging;



import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.elad.themovie.service.repository.storge.model.Movie;

import rx.subjects.ReplaySubject;

/*
    Responsible for creating the DataSource so we can give it to the PagedList.
 */
public class NetMoviesDataSourceFactory extends DataSource.Factory<String,Movie> {

    private final MutableLiveData<NetMoviesPageKeyedDataSource> networkStatus;
    private final NetMoviesPageKeyedDataSource moviesPageKeyedDataSource;
    public NetMoviesDataSourceFactory() {
        this.networkStatus = new MutableLiveData<>();
        moviesPageKeyedDataSource = new NetMoviesPageKeyedDataSource();
    }


    @NonNull
    @Override
    public DataSource<String,Movie> create() {
        networkStatus.postValue(moviesPageKeyedDataSource);
        return moviesPageKeyedDataSource;
    }

    public MutableLiveData<NetMoviesPageKeyedDataSource> getNetworkStatus() {
        return networkStatus;
    }

    public ReplaySubject<Movie> getMovies() {
        return moviesPageKeyedDataSource.getMovies();
    }

}
