package com.ang.acb.movienight.domain.entities

import com.ang.acb.movienight.domain.utils.Constants.CAST_AVATAR_URL

data class Cast(
    val id: Long,
    val movieId: Long,
    val actorName: String?,
    val profileImagePath: String?
) {
    val profileImageUrl = if (profileImagePath != null) CAST_AVATAR_URL + profileImagePath else null
}
