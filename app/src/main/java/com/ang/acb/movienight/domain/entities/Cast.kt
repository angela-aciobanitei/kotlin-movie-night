package com.ang.acb.movienight.domain.entities

import com.ang.acb.movienight.utils.Constants

data class Cast(
    val id: Long,
    val movieId: Long,
    val actorName: String?,
    val profileImagePath: String?
) {
    // todo this should be null if profileImagePath is null
    val profileImageUrl = Constants.CAST_AVATAR_URL + profileImagePath
}
