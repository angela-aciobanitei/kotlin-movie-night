package com.ang.acb.movienight.domain.usecases

import com.ang.acb.movienight.domain.entities.Movie
import com.ang.acb.movienight.domain.gateways.MovieGateway
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteMovieUseCase @Inject constructor(
    private val movieGateway: MovieGateway,
) {
    operator fun invoke(movieId: Long): Flow<Movie?> {
        return movieGateway.getFavoriteMovie(movieId)
    }
}