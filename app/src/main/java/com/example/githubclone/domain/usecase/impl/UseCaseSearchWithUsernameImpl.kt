package com.example.githubclone.domain.usecase.impl

import com.example.githubclone.data.SearchWithUsernameResponseData
import com.example.githubclone.data.repositoryimpl.SearchWithUsernameRepositoryImpl
import com.example.githubclone.domain.usecase.UseCaseSearchWithUsername
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.Flow

class UseCaseSearchWithUsernameImpl(private val repo: SearchWithUsernameRepositoryImpl): UseCaseSearchWithUsername {
    override suspend fun execute(userName: String): Flow<ResultData<SearchWithUsernameResponseData?>> {
        return repo.searchWithUsername(userName)
    }
}