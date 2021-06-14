package com.ang.acb.movienight.data

import com.ang.acb.movienight.domain.MovieGateway
import com.ang.acb.movienight.domain.Movies
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService
) : MovieGateway {

    override suspend fun getPopularMovies(page: Int): Movies {
        val response = movieService.getPopularMovies(page)
        return Movies(
            movies = response.results.asMovies(),
            currentPage = response.page,
            totalPages = response.totalPages
        )
    }

    override suspend fun getTopRatedMovies(page: Int): Movies {
        val response = movieService.getTopRatedMovies(page)
        return Movies(
            movies = response.results.asMovies(),
            currentPage = response.page,
            totalPages = response.totalPages
        )
    }

    override suspend fun getNowPlayingMovies(page: Int): Movies {
        val response = movieService.getNowPlayingMovies(page)
        return Movies(
            movies = response.results.asMovies(),
            currentPage = response.page,
            totalPages = response.totalPages
        )
    }

    override suspend fun getUpcomingMovies(page: Int): Movies {
        val response = movieService.getUpcomingMovies(page)
        return Movies(
            movies = response.results.asMovies(),
            currentPage = response.page,
            totalPages = response.totalPages
        )
    }

    override suspend fun searchMovies(query: String, page: Int): Movies {
        val response = movieService.searchMovies(query, page)
        return Movies(
            movies = response.results.asMovies(),
            currentPage = response.page,
            totalPages = response.totalPages
        )
    }
}