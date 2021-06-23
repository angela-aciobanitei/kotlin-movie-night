package com.ang.acb.movienight.domain.entities

import com.ang.acb.movienight.utils.Constants

data class CastDetails(
    val id: Long,
    val name: String?,
    val birthday: String?,
    val placeOfBirth: String?,
    val biography: String?,
    val profilePath: String?,
) {
    val profileImageUrl = Constants.CAST_AVATAR_URL + profilePath
}