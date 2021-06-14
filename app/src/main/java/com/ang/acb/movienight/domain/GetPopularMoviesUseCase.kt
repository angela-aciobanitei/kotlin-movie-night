package com.ang.acb.movienight.domain

import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieGateway: MovieGateway,
) {

    suspend operator fun invoke(page: Int): Movies {
        return movieGateway.getPopularMovies(page)
    }
}