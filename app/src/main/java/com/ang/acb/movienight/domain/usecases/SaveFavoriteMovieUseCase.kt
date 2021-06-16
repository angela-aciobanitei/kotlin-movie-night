package com.ang.acb.movienight.domain.usecases

import com.ang.acb.movienight.domain.entities.Movie
import com.ang.acb.movienight.domain.gateway.MovieGateway
import javax.inject.Inject

class SaveFavoriteMovieUseCase @Inject constructor(
    private val movieGateway: MovieGateway,
) {
    suspend operator fun invoke(movie: Movie): Long {
        return movieGateway.saveFavoriteMovie(movie)
    }
}