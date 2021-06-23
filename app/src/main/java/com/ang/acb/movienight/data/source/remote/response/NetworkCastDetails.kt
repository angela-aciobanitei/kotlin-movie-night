package com.ang.acb.movienight.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class NetworkCastDetails(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("birthday") val birthday: String?,
    @SerializedName("place_of_birth") val placeOfBirth: String?,
    @SerializedName("biography") val biography: String?,
    @SerializedName("profile_path") val profilePath: String?,
)