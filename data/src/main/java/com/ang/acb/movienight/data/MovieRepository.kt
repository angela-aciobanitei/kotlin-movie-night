package com.ang.acb.movienight.data

import com.ang.acb.movienight.domain.MovieGateway

class MovieRepository(
    private val movieService: MovieService
): MovieGateway {

    override suspend fun getPopularMovies(pageKey: Int) {
        TODO("Not yet implemented")
    }
}