package com.ang.acb.movienight.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * The response from themoviedb.org for movie credits.
 * See: https://developers.themoviedb.org/3/movies/get-movie-credits
 */
data class CreditsResponse(
    @SerializedName("cast") val cast: List<NetworkCast>
)
