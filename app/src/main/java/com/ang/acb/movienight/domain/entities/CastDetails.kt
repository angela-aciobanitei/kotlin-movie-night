package com.ang.acb.movienight.domain.entities

import com.ang.acb.movienight.utils.Constants.CAST_AVATAR_URL
import com.ang.acb.movienight.utils.Constants.CAST_IMDB_URL

data class CastDetails(
    val id: Long,
    val name: String?,
    val birthday: String?,
    val placeOfBirth: String?,
    val biography: String?,
    val profilePath: String?,
    val imdbId: String?
) {
    val profileImageUrl = if (profilePath != null) CAST_AVATAR_URL + profilePath else profilePath
    val imdbUrl = if (imdbId != null) CAST_IMDB_URL + imdbId else null
}