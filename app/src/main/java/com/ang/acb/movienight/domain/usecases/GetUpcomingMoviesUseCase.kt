package com.ang.acb.movienight.domain.usecases

import com.ang.acb.movienight.domain.entities.Movies
import com.ang.acb.movienight.domain.gateway.MovieGateway
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(
    private val movieGateway: MovieGateway,
) {

    suspend operator fun invoke(page: Int): Movies {
        return movieGateway.getUpcomingMovies(page)
    }
}