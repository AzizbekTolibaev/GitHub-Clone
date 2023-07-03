package com.example.githubclone.domain.usecase

import com.example.githubclone.data.GetUserProfileInfo
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.Flow

interface UseCaseUserProfileInfo {

    suspend fun execute(): Flow<ResultData<GetUserProfileInfo?>>
}