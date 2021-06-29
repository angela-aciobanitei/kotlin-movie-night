package com.ang.acb.movienight.domain.gateways

import com.ang.acb.movienight.domain.entities.CastDetails
import com.ang.acb.movienight.domain.entities.Movie
import com.ang.acb.movienight.domain.entities.MovieDetails
import com.ang.acb.movienight.domain.entities.Movies
import kotlinx.coroutines.flow.Flow

interface MovieGateway {
    suspend fun getPopularMovies(page: Int): Movies
    suspend fun getTopRatedMovies(page: Int): Movies
    suspend fun getNowPlayingMovies(page: Int): Movies
    suspend fun getUpcomingMovies(page: Int): Movies
    suspend fun searchMovies(query: String, page: Int): Movies
    suspend fun getAllMovieDetails(movieId: Long): MovieDetails
    suspend fun getSimilarMovies(movieId: Long): Movies
    suspend fun getCastDetails(castId: Long): CastDetails

    suspend fun saveFavoriteMovie(movie: Movie): Long
    suspend fun updateFavoriteFlag(movieId: String, isFavorite: Boolean)
    suspend fun deleteFavoriteMovie(movieId: Long): Int
    suspend fun deleteAllFavoriteMovies()
    fun getFavoriteMovie(movieId: Long): Flow<Movie?>
    fun getAllFavoriteMovies(): Flow<List<Movie>>
}