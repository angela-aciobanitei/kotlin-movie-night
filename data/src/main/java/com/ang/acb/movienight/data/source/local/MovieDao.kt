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

    // When the return type is Flow<T>, querying an empty table throws a null pointer exception.
    // When the return type is Flow<T?>, querying an empty table emits a null value.
    @Query("SELECT * FROM movie WHERE movie.id = :movieId")
    fun getMovie(movieId: Long): Flow<FavoriteMovie?>

    // When the return type is Flow<List<T>>, querying an empty table emits an empty list.
    @Query("SELECT * FROM movie WHERE is_favorite = 1 ORDER BY title")
    fun getAllFavoriteMovies(): Flow<List<FavoriteMovie>>

    @Query("UPDATE movie SET is_favorite = :isFavorite WHERE id = :movieId")
    suspend fun updateFavorite(movieId: String, isFavorite: Boolean)

    @Query("DELETE FROM movie WHERE id = :movieId")
    suspend fun deleteMovieById(movieId: Long): Int

    @Query("DELETE FROM movie")
    suspend fun deleteMovies()
}