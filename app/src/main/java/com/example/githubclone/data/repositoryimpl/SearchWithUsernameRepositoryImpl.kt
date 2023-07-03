package com.example.githubclone.data.repositoryimpl

import com.example.githubclone.data.apiservice.GithubApi
import com.example.githubclone.domain.repository.SearchWithUsernameRepository
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchWithUsernameRepositoryImpl(private val api: GithubApi): SearchWithUsernameRepository {

    override fun searchWithUsername(userName: String) = flow {
        val response = api.searchUsersByUserName(userName)
        val result = response.body()
        if (response.isSuccessful) {
            emit(ResultData.Success(result))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }.flowOn(Dispatchers.IO)


}