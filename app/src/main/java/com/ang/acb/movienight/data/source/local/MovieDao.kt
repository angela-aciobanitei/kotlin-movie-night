package com.ang.acb.movienight.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Interface for database access on [FavoriteMovie] related operations.
 */
@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: FavoriteMovie): Long

    @Query("SELECT * FROM movie WHERE movie.id = :movieId")
    fun getMovie(movieId: Long): Flow<FavoriteMovie>

    @Query("SELECT * FROM movie WHERE is_favorite = 1")
    fun getAllFavoriteMovies(): Flow<List<FavoriteMovie>>

    @Query("UPDATE movie SET is_favorite = :isFavorite WHERE id = :movieId")
    suspend fun updateFavorite(movieId: String, isFavorite: Boolean)

    @Query("DELETE FROM movie WHERE id = :movieId")
    suspend fun deleteMovieById(movieId: String): Int

    @Query("DELETE FROM movie")
    suspend fun deleteMovies()
}