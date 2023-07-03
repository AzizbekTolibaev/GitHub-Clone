package com.example.githubclone.data

import com.google.gson.annotations.SerializedName

data class SearchWithRepositoriesResponseData(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<SearchWithRepositoriesItemsData>
)

data class SearchWithRepositoriesItemsData(
    val name: String,
    @SerializedName("stargazers_count")
    val starCount: Int,
    val language: String,
    val owner: SearchWithRepositoriesOwnerData,
    val description: String
)

data class SearchWithRepositoriesOwnerData(
    @SerializedName("login")
    val userName: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)