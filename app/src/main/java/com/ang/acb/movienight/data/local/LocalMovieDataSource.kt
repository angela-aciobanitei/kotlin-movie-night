package com.ang.acb.movienight.data.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalMovieDataSource @Inject constructor(
    private val movieDao: MovieDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun saveFavoriteMovie(movie: FavoriteMovie): Long {
        return withContext(ioDispatcher) {
            movieDao.insertMovie(movie)
        }
    }

    fun getFavoriteMovie(movieId: Long): Flow<FavoriteMovie> {
        return movieDao.getMovie(movieId)
    }

    fun getAllFavoriteMovies(): Flow<List<FavoriteMovie>> {
        return movieDao.getAllFavoriteMovies()
    }

    suspend fun updateFavoriteFlag(movieId: String, isFavorite: Boolean) {
        withContext(ioDispatcher) {
            movieDao.updateFavorite(movieId, isFavorite)
        }
    }

    suspend fun deleteFavoriteMovie(movieId: String): Int {
        return withContext(ioDispatcher) {
            movieDao.deleteMovieById(movieId)
        }
    }

    suspend fun deleteAllFavoriteMovies() {
        withContext(ioDispatcher) {
            movieDao.deleteMovies()
        }
    }
}