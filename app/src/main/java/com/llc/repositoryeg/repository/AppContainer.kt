
package com.llc.repositoryeg

import retrofit2.Retrofit
import com.llc.repositoryeg.network.MovieAPIService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val movieRepository: MovieRepository
}

class DefaultAppContainer : AppContainer {

     val MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(MOVIE_BASE_URL)
        .build()

    private val retrofitService: MovieAPIService by lazy {
        retrofit.create(MovieAPIService::class.java)
    }

    /**
     * DI implementation for Mars photos repository
     */
    override val movieRepository: MovieRepository by lazy {
        //NetworkMarsPhotosRepository(retrofitService)
        DefaultMovieRepository(retrofitService)
    }
}

@Module
@InstallIn(ActivityComponent::class)
abstract class AppContainerModule {

    @Binds
    abstract fun bindAppContainer(
        detaultAppContainer: DefaultAppContainer
    ): AppContainer
}
