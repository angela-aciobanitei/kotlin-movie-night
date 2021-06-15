package com.ang.acb.movienight.domain

import javax.inject.Inject

class SaveFavoriteMovieUseCase @Inject constructor(
    private val movieGateway: MovieGateway,
) {
    suspend operator fun invoke(movie: Movie): Long {
        return movieGateway.saveFavoriteMovie(movie)
    }
}