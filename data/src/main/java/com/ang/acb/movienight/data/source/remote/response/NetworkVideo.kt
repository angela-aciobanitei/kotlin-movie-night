package com.ang.acb.movienight.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class NetworkVideo(
    @SerializedName("id") val id: String,
    @SerializedName("key") val key: String?,
    @SerializedName("name") val name: String?
)