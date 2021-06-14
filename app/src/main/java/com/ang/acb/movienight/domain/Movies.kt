package com.ang.acb.movienight.domain

data class Movies(
    val movies: List<Movie>,
    val currentPage: Int,
    val totalPages: Int,
)