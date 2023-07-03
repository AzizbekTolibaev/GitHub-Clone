package com.example.githubclone.data.repositoryimpl

import com.example.githubclone.data.apiservice.GithubApi
import com.example.githubclone.domain.repository.LoginRepository
import com.example.githubclone.utils.Constants
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRepositoryImpl(private val api: GithubApi): LoginRepository {

    override suspend fun getAccessToken(code: String) = flow {

        val response = api.getAccessToken(Constants.clientId, Constants.clientSecret, code)
        val result = response.body()?.accessToken
        if (response.isSuccessful) {
            emit(ResultData.Success(result))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }.flowOn(Dispatchers.IO)
}
