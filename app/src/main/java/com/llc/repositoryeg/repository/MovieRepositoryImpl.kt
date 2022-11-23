package com.llc.repositoryeg.repository

import com.llc.repositoryeg.model.MovieModel
import com.llc.repositoryeg.model.MoviesResponseModel
import com.llc.repositoryeg.network.API_KEY
import com.llc.repositoryeg.network.MovieAPIService
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieAPIService: MovieAPIService
) : MovieRepository {
    override suspend fun getNowShowingMovies(): MoviesResponseModel<MovieModel> {
        return movieAPIService.getNowPlaying(apiKey = API_KEY)
    }

    override suspend fun getPopularMovies(): MoviesResponseModel<MovieModel> {
        return movieAPIService.getPopular(api_key = API_KEY)
    }
}