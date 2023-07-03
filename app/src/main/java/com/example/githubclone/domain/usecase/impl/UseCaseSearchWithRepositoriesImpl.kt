package com.example.githubclone.domain.usecase.impl

import com.example.githubclone.data.SearchWithRepositoriesResponseData
import com.example.githubclone.domain.repository.SearchWithRepositoriesRepository
import com.example.githubclone.domain.usecase.UseCaseSearchWithRepositories
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.Flow

class UseCaseSearchWithRepositoriesImpl(private val repo: SearchWithRepositoriesRepository): UseCaseSearchWithRepositories {
    override suspend fun execute(name: String): Flow<ResultData<SearchWithRepositoriesResponseData?>> {
        return repo.searchWithRepositories(name)
    }
}