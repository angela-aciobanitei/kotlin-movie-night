package com.ang.acb.movienight.data

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Defines the REST API access points for Retrofit.
 */
interface MovieService {
    /**
     * Get a list of the current popular movies on TMDb.
     */
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): MovieListResponse

    /**
     * Get the top rated movies on TMDb.
     */
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int): MovieListResponse

    /**
     * Get a list of movies in theatres.
     */
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: Int): MovieListResponse

    /**
     * Get a list of upcoming movies in theatres.
     */
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int): MovieListResponse
}