package com.ang.acb.movienight.domain.entities

data class Trailer(
    val id: String,
    val movieId: Long,
    val key: String?,
    val name: String?
)