package com.example.githubclone.data

import com.google.gson.annotations.SerializedName

data class AccessTokenData(
    @SerializedName("access_token")
    val accessToken: String,
)