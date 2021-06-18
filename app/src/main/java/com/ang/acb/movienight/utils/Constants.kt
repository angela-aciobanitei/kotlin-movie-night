package com.ang.acb.movienight.utils

object Constants {
    const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"

    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    const val IMAGE_SIZE_W185 = "w185"
    const val IMAGE_SIZE_W780 = "w780"

    const val CAST_AVATAR_URL = IMAGE_BASE_URL + IMAGE_SIZE_W185
    const val POSTER_URL = IMAGE_BASE_URL + IMAGE_SIZE_W185
    const val BACKDROP_URL = IMAGE_BASE_URL + IMAGE_SIZE_W780

    const val YOUTUBE_WEB_BASE_URL = "https://www.youtube.com/watch?v="
    const val YOUTUBE_APP_BASE_URL = "vnd.youtube:"
    const val YOUTUBE_TRAILER_THUMBNAIL_BASE_URL = "https://img.youtube.com/vi/"
    const val YOUTUBE_TRAILER_THUMBNAIL_HQ = "/hqdefault.jpg"
}