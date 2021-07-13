package com.ang.acb.movienight.domain.usecases

import com.ang.acb.movienight.domain.entities.CastDetails
import com.ang.acb.movienight.domain.gateways.MovieGateway
import javax.inject.Inject

class GetCastDetailsUseCase @Inject constructor(
    private val movieGateway: MovieGateway,
) {

    suspend operator fun invoke(castId: Long): CastDetails {
        return movieGateway.getCastDetails(castId)
    }
}