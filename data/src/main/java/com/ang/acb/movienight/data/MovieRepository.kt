package com.ang.acb.movienight.data

import com.ang.acb.movienight.domain.MovieGateway
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService
) : MovieGateway {

    override suspend fun getPopularMovies(pageKey: Int) {
        TODO("Not yet implemented")
    }
}