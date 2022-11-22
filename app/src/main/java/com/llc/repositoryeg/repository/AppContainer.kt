
package com.llc.repositoryeg

import retrofit2.Retrofit
import com.llc.repositoryeg.network.MovieAPIService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

interface AppContainer {
    val movieRepository: MovieRepository
}

class DefaultAppContainer : AppContainer {

     val MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/"

    /*private val moshi = Moshi.Builder()
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
    }*/



    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .also {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                }
            }
            .readTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MOVIE_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieAPIService(retrofit: Retrofit): MovieAPIService {
       // return retrofit.create(MovieAPIService::class.java)
        var retrofitService=retrofit.create(MovieAPIService::class.java)
        return retrofitService
    }

    override val movieRepository: MovieRepository
        get() = TODO("Not yet implemented")

}


@Module
@InstallIn(ActivityComponent::class)
abstract class AppContainerModule {

    @Binds
    abstract fun bindAppContainer(
        detaultAppContainer: DefaultAppContainer
    ): AppContainer
}
