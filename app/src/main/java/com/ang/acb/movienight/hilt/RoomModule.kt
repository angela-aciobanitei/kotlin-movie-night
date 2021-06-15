package com.ang.acb.movienight.hilt

import android.content.Context
import androidx.room.Room
import com.ang.acb.movienight.data.local.MovieDao
import com.ang.acb.movienight.data.local.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MoviesDatabase::class.java,
            "movies.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(database: MoviesDatabase): MovieDao {
        return database.movieDao
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}