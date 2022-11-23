package com.llc.repositoryeg

import com.llc.repositoryeg.model.MovieModel
import com.llc.repositoryeg.model.MoviesResponseModel
import com.llc.repositoryeg.network.MovieAPIService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject
import javax.inject.Singleton

interface MovieRepository {
    //suspend fun getMarsPhotos(): List<MovieModel>
    suspend fun getNowShowingMovies(): MoviesResponseModel<MovieModel>
    suspend fun getPopularMovies(): MoviesResponseModel<MovieModel>
}

@Module
@InstallIn(ActivityComponent::class)
class DefaultMovieRepository @Inject constructor( private val movieApiService: MovieAPIService) :
    MovieRepository {

    @Provides
    @Singleton
    override suspend fun getNowShowingMovies(): MoviesResponseModel<MovieModel> {
        // return MarsApi.retrofitService.getPhotos()
        //return marsApiService.getPhotos()
        return movieApiService.getNowPlaying()
    }

    @Provides
    @Singleton
    override suspend fun getPopularMovies(): MoviesResponseModel<MovieModel> {
        return movieApiService.getPopular()
    }
}

@Module
@InstallIn(ActivityComponent::class)
abstract class MovieRepositoryModule {

    @Binds
    abstract fun bindMovieRepository(
        defaultMovieRepository: DefaultMovieRepository
    ): MovieRepository
}