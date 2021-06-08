package com.ang.acb.movienight.hilt

import com.ang.acb.movienight.data.MovieRepository
import com.ang.acb.movienight.data.MovieService
import com.ang.acb.movienight.domain.MovieGateway
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    @Provides
    @Singleton
    fun provideMovieGateway(
        movieRepository: MovieRepository
    ): MovieGateway = movieRepository

    @Provides
    @Singleton
    fun provideMovieService(
        retrofit: Retrofit
    ): MovieService = retrofit.create(MovieService::class.java)

}