package com.llc.repositoryeg.repository

import com.llc.repositoryeg.model.MovieModel
import com.llc.repositoryeg.model.MoviesResponseModel

interface MovieRepository {
    //suspend fun getMarsPhotos(): List<MovieModel>
    suspend fun getNowShowingMovies(): MoviesResponseModel<MovieModel>
    suspend fun getPopularMovies(): MoviesResponseModel<MovieModel>
}
