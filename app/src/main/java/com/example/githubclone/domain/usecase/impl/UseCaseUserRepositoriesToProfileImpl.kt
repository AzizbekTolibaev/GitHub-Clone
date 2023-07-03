package com.example.githubclone.domain.usecase.impl

import com.example.githubclone.data.RepositoryResponseData
import com.example.githubclone.domain.repository.ProfileRepository
import com.example.githubclone.domain.usecase.UseCaseUserRepositoriesToProfile
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.Flow

class UseCaseUserRepositoriesToProfileImpl(private val repo: ProfileRepository):
    UseCaseUserRepositoriesToProfile {
    override suspend fun execute(): Flow<ResultData<MutableList<RepositoryResponseData>?>> {
        return repo.getUserRepositories()
    }
}