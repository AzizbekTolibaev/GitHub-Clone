package com.example.githubclone.domain.repository

import com.example.githubclone.data.SearchWithUsernameResponseData
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.Flow

interface SearchWithUsernameRepository {

    fun searchWithUsername(userName: String): Flow<ResultData<SearchWithUsernameResponseData?>>
}