package com.example.githubclone.domain.repository

import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun getAccessToken(code: String): Flow<ResultData<String?>>
}