package com.llc.repositoryeg.network

import com.llc.moviebd.data.model.CreditModel
import com.llc.moviebd.data.model.MovieDetailModel
import com.llc.repositoryeg.model.MovieModel
import com.llc.repositoryeg.model.MoviesResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "9c9e4b9082cd70edd1ed7afab4f198b6"
const val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

const val MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/"
/*
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//The Retrofit object with the Moshi converter.
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(MOVIE_BASE_URL)
    .build()*/

interface MovieAPIService {
    @GET("now_playing")
    suspend fun getNowPlaying(@Query("api_key") apiKey: String = API_KEY): MoviesResponseModel<MovieModel>

    @GET("popular")
    suspend fun getPopular(@Query("api_key") api_key: String = API_KEY): MoviesResponseModel<MovieModel>

    @GET("{movie_id}")
    suspend fun loadMovieDetail(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = API_KEY
    ): MovieDetailModel?

    @GET("{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): CreditModel
}

/*
object MovieAPI {
    val retrofitService: MovieAPIService by lazy {
        retrofit.create(MovieAPIService::class.java)
    }
}
*/




