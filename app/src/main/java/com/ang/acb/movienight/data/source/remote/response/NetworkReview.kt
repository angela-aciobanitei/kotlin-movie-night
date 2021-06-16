package com.ang.acb.movienight.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class NetworkReview(
    @SerializedName("id") val id: String,
    @SerializedName("author") val author: String?,
    @SerializedName("content") val content: String?,
    @SerializedName("url") val url: String?
)