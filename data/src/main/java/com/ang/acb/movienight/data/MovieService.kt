package com.ang.acb.movienight.data

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("page") pageNumber: Int
    ): MovieListResponse

}