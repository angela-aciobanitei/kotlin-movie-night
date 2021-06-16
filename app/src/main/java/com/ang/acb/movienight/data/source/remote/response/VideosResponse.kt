package com.ang.acb.movienight.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * The response from themoviedb.org when getting the videos associated with a specific movie.
 * See: https://developers.themoviedb.org/3/movies/get-movie-videos
 */
data class VideosResponse(
    @SerializedName("results") val videos: List<NetworkVideo>
)


