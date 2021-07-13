package com.ang.acb.movienight.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteMovie::class], version = 2, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract val movieDao: MovieDao
}
