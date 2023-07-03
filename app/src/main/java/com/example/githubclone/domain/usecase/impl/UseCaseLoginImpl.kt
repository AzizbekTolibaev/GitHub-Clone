package com.example.githubclone.domain.usecase.impl

import com.example.githubclone.domain.repository.LoginRepository
import com.example.githubclone.domain.usecase.UseCaseLogin
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.Flow

class UseCaseLoginImpl(private val repo: LoginRepository): UseCaseLogin {
    override suspend fun execute(code: String): Flow<ResultData<String?>> {
        return repo.getAccessToken(code)
    }
}