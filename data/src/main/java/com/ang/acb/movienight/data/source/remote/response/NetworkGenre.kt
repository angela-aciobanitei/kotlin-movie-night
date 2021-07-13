package com.ang.acb.movienight.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class NetworkGenre(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String?
)