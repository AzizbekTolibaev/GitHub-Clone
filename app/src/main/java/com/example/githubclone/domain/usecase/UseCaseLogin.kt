package com.example.githubclone.domain.usecase

import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.Flow

interface UseCaseLogin {

    suspend fun execute(code: String): Flow<ResultData<String?>>
}