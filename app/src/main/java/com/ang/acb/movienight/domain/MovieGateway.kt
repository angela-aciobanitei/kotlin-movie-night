package com.ang.acb.movienight.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface MovieGateway {
    fun getMovies(filter: MovieFilter): Flow<PagingData<Movie>>
    fun searchMovies(query: String): Flow<PagingData<Movie>>
}