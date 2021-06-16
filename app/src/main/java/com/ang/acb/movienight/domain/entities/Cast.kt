package com.ang.acb.movienight.domain.entities

data class Cast(
    val id: Long,
    val movieId: Long,
    val actorName: String?,
    val profileImagePath: String?
)
