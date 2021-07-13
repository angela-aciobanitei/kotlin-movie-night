package com.ang.acb.movienight.domain.usecases

import com.ang.acb.movienight.domain.entities.Movie
import com.ang.acb.movienight.domain.gateways.MovieGateway
import javax.inject.Inject

class GetSimilarMoviesUseCase @Inject constructor(
    private val movieGateway: MovieGateway,
) {

    suspend operator fun invoke(movieId: Long): List<Movie> {
        return movieGateway.getSimilarMovies(movieId).movies
    }
}