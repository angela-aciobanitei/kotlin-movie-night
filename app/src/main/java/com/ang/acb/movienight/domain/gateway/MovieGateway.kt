package com.ang.acb.movienight.domain.gateway

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

    suspend fun saveFavoriteMovie(movie: Movie): Long
    fun getFavoriteMovie(movieId: Long): Flow<Movie>
    fun getAllFavoriteMovies(): Flow<List<Movie>>
    suspend fun updateFavoriteFlag(movieId: String, isFavorite: Boolean)
    suspend fun deleteFavoriteMovie(movieId: String): Int
    suspend fun deleteAllFavoriteMovies()
}