package com.example.githubclone.domain.usecase

import com.example.githubclone.data.RepositoryResponseData
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.Flow

interface UseCaseRepositories {

    suspend fun execute(): Flow<ResultData<MutableList<RepositoryResponseData>?>>
}