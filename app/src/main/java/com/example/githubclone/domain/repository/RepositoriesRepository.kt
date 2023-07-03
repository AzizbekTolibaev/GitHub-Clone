package com.example.githubclone.domain.repository

import com.example.githubclone.data.RepositoryResponseData
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.Flow

interface RepositoriesRepository {

    suspend fun getUserRepositories(): Flow<ResultData<MutableList<RepositoryResponseData>?>>
}