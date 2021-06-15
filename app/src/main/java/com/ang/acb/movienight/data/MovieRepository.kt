package com.ang.acb.movienight.data

import com.ang.acb.movienight.domain.MovieGateway
import com.ang.acb.movienight.domain.Movies
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService
) : MovieGateway {

    override suspend fun getPopularMovies(page: Int): Movies {
        return movieService.getPopularMovies(page).asMovies()
    }

    override suspend fun getTopRatedMovies(page: Int): Movies {
        return movieService.getTopRatedMovies(page).asMovies()
    }

    override suspend fun getNowPlayingMovies(page: Int): Movies {
        return movieService.getNowPlayingMovies(page).asMovies()
    }

    override suspend fun getUpcomingMovies(page: Int): Movies {
        return movieService.getUpcomingMovies(page).asMovies()
    }

    override suspend fun searchMovies(query: String, page: Int): Movies {
        return movieService.searchMovies(query, page).asMovies()
    }
}