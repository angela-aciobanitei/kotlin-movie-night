package com.ang.acb.movienight.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * The response from themoviedb.org for movie details.
 *
 * Note: Since the "movie" method supports the query parameter
 * append_to_response, we can use it to issue multiple requests in
 * order to include the movie credits, videos and reviews in our response.
 *
 * See: https://developers.themoviedb.org/3/movies/get-movie-details
 * See: https://developers.themoviedb.org/3/getting-started/append-to-response
 */
data class NetworkMovieDetails(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("vote_count") val voteCount: Int?,
    @SerializedName("genres") val genres: List<NetworkGenre>?,
    @SerializedName("credits") val creditsResponse: CreditsResponse,
    @SerializedName("videos") val videosResponse: VideosResponse,
)