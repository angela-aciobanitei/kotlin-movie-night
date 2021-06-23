package com.ang.acb.movienight.domain.entities

import com.ang.acb.movienight.utils.Constants

data class CastDetails(
    val id: Long,
    val name: String?,
    val birthday: String?,
    val placeOfBirth: String?,
    val biography: String?,
    val profilePath: String?,
    val imdbId: String?
) {
    // todo this should be null if profilePath is null
    val profileImageUrl = Constants.CAST_AVATAR_URL + profilePath
    val imdbUrl = if (imdbId != null) "https://www.imdb.com/name/$imdbId" else null
}