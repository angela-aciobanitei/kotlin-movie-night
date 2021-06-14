package com.ang.acb.movienight.domain

import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val movieGateway: MovieGateway,
) {

    suspend fun searchMovies(query: String, page: Int): Movies {
        return movieGateway.searchMovies(query, page)
    }
}