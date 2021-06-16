package com.ang.acb.movienight.domain.usecases

import com.ang.acb.movienight.domain.entities.Movie
import com.ang.acb.movienight.domain.gateways.MovieGateway
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavoriteMoviesUseCase @Inject constructor(
    private val movieGateway: MovieGateway,
) {

    operator fun invoke(): Flow<List<Movie>> {
        return movieGateway.getAllFavoriteMovies()
    }
}