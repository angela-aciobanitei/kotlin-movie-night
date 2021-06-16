package com.ang.acb.movienight.domain.entities

data class Genre(
    val id: Long,
    val movieId: Long,
    val name: String?
)