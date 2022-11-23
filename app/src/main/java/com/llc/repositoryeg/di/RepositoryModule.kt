package com.llc.repositoryeg.di

import com.llc.repositoryeg.network.MovieAPIService
import com.llc.repositoryeg.repository.MovieRepository
import com.llc.repositoryeg.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(movieApiService: MovieAPIService): MovieRepository {
        return MovieRepositoryImpl(movieApiService)
    }
}

/*
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepositoryImpl(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}
*/
