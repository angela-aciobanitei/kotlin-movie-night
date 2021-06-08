package com.ang.acb.movienight.data

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("results") val results: List<NetworkMovie>,
    @SerializedName("page") val page: Int
)
