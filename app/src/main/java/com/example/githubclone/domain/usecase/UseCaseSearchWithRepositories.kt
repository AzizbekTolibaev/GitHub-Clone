package com.example.githubclone.domain.usecase

import com.example.githubclone.data.SearchWithRepositoriesResponseData
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.Flow

interface UseCaseSearchWithRepositories {

    suspend fun execute(name: String): Flow<ResultData<SearchWithRepositoriesResponseData?>>
}