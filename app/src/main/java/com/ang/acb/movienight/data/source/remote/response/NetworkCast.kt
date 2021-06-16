package com.ang.acb.movienight.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class NetworkCast(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val actorName: String?,
    @SerializedName("profile_path") val profileImagePath: String?
)