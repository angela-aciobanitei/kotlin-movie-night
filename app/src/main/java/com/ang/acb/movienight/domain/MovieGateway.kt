package com.ang.acb.movienight.domain

interface MovieGateway {
    suspend fun getPopularMovies(page: Int): Movies
    suspend fun getTopRatedMovies(page: Int): Movies
    suspend fun getNowPlayingMovies(page: Int): Movies
    suspend fun getUpcomingMovies(page: Int): Movies
    suspend fun searchMovies(query: String, page: Int): Movies
}