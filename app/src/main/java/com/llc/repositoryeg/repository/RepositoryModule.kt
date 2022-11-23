package com.llc.repositoryeg.repository

import com.llc.repositoryeg.model.MovieModel
import com.llc.repositoryeg.model.MoviesResponseModel
import com.llc.repositoryeg.network.MovieAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    suspend fun provideRepository(movieApiService: MovieAPIService): MoviesResponseModel<MovieModel> {
        return movieApiService.getNowPlaying()
    }
}