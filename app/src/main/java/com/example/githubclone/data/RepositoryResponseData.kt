package com.example.githubclone.data

import com.google.gson.annotations.SerializedName

data class RepositoryResponseData(
    val name: String,
    @SerializedName("stargazers_count")
    val starCount: Int,
    val language: String,
    val owner: RepositoryOwnerData,
    val description: String
)

data class RepositoryOwnerData(
    @SerializedName("login")
    val userName: String,
    val avatar_url: String
)
