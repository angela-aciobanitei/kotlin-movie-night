package com.ang.acb.movienight.domain

interface MovieGateway {
    suspend fun getPopularMovies(pageKey: Int)
}