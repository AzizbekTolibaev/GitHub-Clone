package com.example.githubclone.domain.usecase

import com.example.githubclone.data.SearchWithUsernameResponseData
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.Flow

interface UseCaseSearchWithUsername {

    suspend fun execute(userName: String): Flow<ResultData<SearchWithUsernameResponseData?>>
}