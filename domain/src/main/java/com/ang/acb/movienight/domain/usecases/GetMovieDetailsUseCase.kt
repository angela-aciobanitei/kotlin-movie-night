package com.ang.acb.movienight.domain.usecases

import com.ang.acb.movienight.domain.entities.MovieDetails
import com.ang.acb.movienight.domain.gateways.MovieGateway
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieGateway: MovieGateway,
) {

    suspend operator fun invoke(movieId: Long): MovieDetails {
        return movieGateway.getAllMovieDetails(movieId)
    }
}