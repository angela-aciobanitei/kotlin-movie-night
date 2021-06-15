package com.ang.acb.movienight.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavoriteMoviesUseCase @Inject constructor(
    private val movieGateway: MovieGateway,
) {

    operator fun invoke(): Flow<List<Movie>> {
        return movieGateway.getAllFavoriteMovies()
    }
}