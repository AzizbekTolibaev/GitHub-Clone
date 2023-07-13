package com.example.githubclone.data

import com.google.gson.annotations.SerializedName

data class  GetUserProfileInfoData(
    @SerializedName("login")
    val userName: String,
    val name: String,
    val avatar_url: String,
    val bio: String,
    val followers: Int,
    val following: Int
)
