package com.ang.acb.movienight.data.source.remote

import com.ang.acb.movienight.data.source.remote.response.MoviesResponse
import com.ang.acb.movienight.data.source.remote.response.NetworkCastDetails
import com.ang.acb.movienight.data.source.remote.response.NetworkMovieDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): MoviesResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int): MoviesResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: Int): MoviesResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int): MoviesResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ): MoviesResponse

    /**
     * Get detailed information about a movie.
     * Use query parameter "append_to_response" to issue multiple requests.
     * https://developers.themoviedb.org/3/getting-started/append-to-response
     */
    @GET("movie/{id}?append_to_response=videos,credits")
    suspend fun getAllMovieDetails(@Path("id") id: Long): NetworkMovieDetails

    @GET("person/{person_id}")
    suspend fun getCastDetails(@Path("person_id") id: Long): NetworkCastDetails

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(@Path("movie_id") id: Long): MoviesResponse
}