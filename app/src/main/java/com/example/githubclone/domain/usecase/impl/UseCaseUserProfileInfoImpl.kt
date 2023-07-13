package com.example.githubclone.domain.usecase.impl

import com.example.githubclone.data.GetUserProfileInfoData
import com.example.githubclone.domain.repository.ProfileRepository
import com.example.githubclone.domain.usecase.UseCaseUserProfileInfo
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.Flow

class UseCaseUserProfileInfoImpl(private val repo: ProfileRepository): UseCaseUserProfileInfo {
    override suspend fun execute(): Flow<ResultData<GetUserProfileInfoData?>> {
        return repo.getUserProfileInfo()
    }
}