package com.ang.acb.movienight.domain.usecases

import com.ang.acb.movienight.domain.entities.Movies
import com.ang.acb.movienight.domain.gateway.MovieGateway
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val movieGateway: MovieGateway,
) {

    suspend operator fun invoke(query: String, page: Int): Movies {
        return movieGateway.searchMovies(query, page)
    }
}