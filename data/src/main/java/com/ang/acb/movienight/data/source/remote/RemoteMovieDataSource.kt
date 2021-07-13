package com.ang.acb.movienight.data.source.remote

import com.ang.acb.movienight.domain.entities.CastDetails
import com.ang.acb.movienight.domain.entities.MovieDetails
import com.ang.acb.movienight.domain.entities.Movies
import javax.inject.Inject

class RemoteMovieDataSource @Inject constructor(
    private val movieService: MovieService
) {

    suspend fun getPopularMovies(page: Int): Movies {
        return movieService.getPopularMovies(page).asMovies()
    }

    suspend fun getTopRatedMovies(page: Int): Movies {
        return movieService.getTopRatedMovies(page).asMovies()
    }

    suspend fun getNowPlayingMovies(page: Int): Movies {
        return movieService.getNowPlayingMovies(page).asMovies()
    }

    suspend fun getUpcomingMovies(page: Int): Movies {
        return movieService.getUpcomingMovies(page).asMovies()
    }

    suspend fun searchMovies(query: String, page: Int): Movies {
        return movieService.searchMovies(query, page).asMovies()
    }

    suspend fun getAllMovieDetails(movieId: Long): MovieDetails {
        return movieService.getAllMovieDetails(movieId).asMovieDetails()
    }

    suspend fun getSimilarMovies(movieId: Long): Movies {
        return movieService.getSimilarMovies(movieId).asMovies()
    }

    suspend fun getCastDetails(castId: Long): CastDetails {
        return movieService.getCastDetails(castId).asCastDetails()
    }
}