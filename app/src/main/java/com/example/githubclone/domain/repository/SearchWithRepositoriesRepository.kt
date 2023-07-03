package com.example.githubclone.domain.repository

import com.example.githubclone.data.SearchWithRepositoriesResponseData
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.Flow

interface SearchWithRepositoriesRepository {

    suspend fun searchWithRepositories(name: String): Flow<ResultData<SearchWithRepositoriesResponseData?>>
}