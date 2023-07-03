package com.example.githubclone.data

import com.google.gson.annotations.SerializedName

data class SearchWithUsernameResponseData(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<SearchWithUsernameItemsData>
)

data class SearchWithUsernameItemsData(
    @SerializedName("login")
    val userName: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)
