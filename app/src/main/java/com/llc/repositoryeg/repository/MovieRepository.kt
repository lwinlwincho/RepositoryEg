package com.llc.repositoryeg

import com.llc.repositoryeg.model.MovieModel
import com.llc.repositoryeg.model.MoviesResponseModel
import com.llc.repositoryeg.network.MovieAPIService

interface MovieRepository{
    //suspend fun getMarsPhotos(): List<MovieModel>
    suspend fun getNowShowingMovies(): MoviesResponseModel<MovieModel>
    suspend fun getPopularMovies(): MoviesResponseModel<MovieModel>
}

class DefaultMovieRepository(private val movieApiService: MovieAPIService): MovieRepository {
    override suspend fun getNowShowingMovies(): MoviesResponseModel<MovieModel> {
        // return MarsApi.retrofitService.getPhotos()
        //return marsApiService.getPhotos()
        return movieApiService.getNowPlaying()
    }

    override suspend fun getPopularMovies(): MoviesResponseModel<MovieModel> {
        return movieApiService.getPopular()
    }
}