package com.ang.acb.movienight.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface MovieGateway {
    fun getPopularMovies(): Flow<PagingData<Movie>>
}