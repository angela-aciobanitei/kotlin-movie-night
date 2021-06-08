package com.ang.acb.movienight.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Defines the REST API access points for Retrofit.
 */
interface MovieService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int): MoviesResponse

    @GET("movie/popular")
    suspend fun getPopularMoviesSuspend(): MoviesResponse

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("page") page: Int): Response<MoviesResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("page") page: Int): Response<MoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("page") page: Int): Response<MoviesResponse>
}