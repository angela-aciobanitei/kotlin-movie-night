package com.ang.acb.movienight.domain

import kotlinx.coroutines.flow.Flow

interface MovieGateway {
    suspend fun getPopularMovies() : List<Movie>
}