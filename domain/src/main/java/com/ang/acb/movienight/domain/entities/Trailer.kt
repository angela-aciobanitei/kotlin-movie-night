package com.ang.acb.movienight.domain.entities

private const val YOUTUBE_APP_BASE_URL = "vnd.youtube:"
private const val YOUTUBE_WEB_BASE_URL = "https://www.youtube.com/watch?v="
private const val YOUTUBE_TRAILER_THUMBNAIL_BASE_URL = "https://img.youtube.com/vi/"
private const val YOUTUBE_TRAILER_THUMBNAIL_HQ_SUFFIX = "/hqdefault.jpg"

data class Trailer(
    val id: String,
    val movieId: Long,
    val key: String?,
    val name: String?
) {
    val youTubeThumbnailUrl = if (key != null) {
        YOUTUBE_TRAILER_THUMBNAIL_BASE_URL + key + YOUTUBE_TRAILER_THUMBNAIL_HQ_SUFFIX
    } else null

    val youTubeAppUrl = if (key != null) YOUTUBE_APP_BASE_URL + key else null
    val youTubeWebUrl = if (key != null) YOUTUBE_WEB_BASE_URL + key else null
}