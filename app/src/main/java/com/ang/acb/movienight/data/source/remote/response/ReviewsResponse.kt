package com.ang.acb.movienight.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * The response from themoviedb.org for movie reviews.
 * See: https://developers.themoviedb.org/3/movies/get-movie-reviews
 */
data class ReviewsResponse(
    @SerializedName("results") val reviews: List<NetworkReview>
)
