package com.ang.acb.movienight.domain

import kotlinx.coroutines.flow.Flow

interface MovieGateway {
    fun getPopularMovies(pageKey: Int) : List<Movie>
}