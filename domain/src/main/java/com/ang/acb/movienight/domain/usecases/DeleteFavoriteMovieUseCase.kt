package com.ang.acb.movienight.domain.usecases

import com.ang.acb.movienight.domain.gateways.MovieGateway
import javax.inject.Inject

class DeleteFavoriteMovieUseCase @Inject constructor(
    private val movieGateway: MovieGateway,
) {
    suspend operator fun invoke(movieId: Long): Int {
        return movieGateway.deleteFavoriteMovie(movieId)
    }
}