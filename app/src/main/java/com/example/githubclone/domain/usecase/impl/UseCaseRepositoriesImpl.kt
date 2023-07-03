package com.example.githubclone.domain.usecase.impl

import com.example.githubclone.data.RepositoryResponseData
import com.example.githubclone.domain.repository.RepositoriesRepository
import com.example.githubclone.domain.usecase.UseCaseRepositories
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.Flow

class UseCaseRepositoriesImpl(private val repo: RepositoriesRepository): UseCaseRepositories {
    override suspend fun execute(): Flow<ResultData<MutableList<RepositoryResponseData>?>> {
        return repo.getUserRepositories()
    }
}