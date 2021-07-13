package com.ang.acb.movienight.domain.entities

import com.ang.acb.movienight.domain.utils.Constants.CAST_AVATAR_URL
import com.ang.acb.movienight.domain.utils.Constants.CAST_IMDB_URL

data class CastDetails(
    val id: Long,
    val name: String?,
    val birthday: String?,
    val placeOfBirth: String?,
    val biography: String?,
    val profileImagePath: String?,
    val imdbId: String?
) {
    val profileImageUrl = if (profileImagePath != null) CAST_AVATAR_URL + profileImagePath else null
    val imdbUrl = if (imdbId != null) CAST_IMDB_URL + imdbId else null
}