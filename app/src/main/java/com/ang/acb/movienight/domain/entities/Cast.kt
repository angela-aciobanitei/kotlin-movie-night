package com.ang.acb.movienight.domain.entities

import com.ang.acb.movienight.utils.Constants

data class Cast(
    val id: Long,
    val movieId: Long,
    val actorName: String?,
    val profileImagePath: String?
) {
    val profileImageUrl = if (profileImagePath != null) {
        Constants.CAST_AVATAR_URL + profileImagePath
    } else {
        profileImagePath
    }
}
