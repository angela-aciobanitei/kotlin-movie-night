package com.ang.acb.movienight.di

import com.ang.acb.movienight.data.MovieRepository
import com.ang.acb.movienight.domain.gateways.MovieGateway
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideMovieGateway(
        movieRepository: MovieRepository
    ): MovieGateway = movieRepository
}