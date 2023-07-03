package com.example.githubclone.domain.repository

import com.example.githubclone.data.GetUserProfileInfo
import com.example.githubclone.data.RepositoryResponseData
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getUserProfileInfo(): Flow<ResultData<GetUserProfileInfo?>>

    suspend fun getUserRepositories(): Flow<ResultData<MutableList<RepositoryResponseData>?>>
}