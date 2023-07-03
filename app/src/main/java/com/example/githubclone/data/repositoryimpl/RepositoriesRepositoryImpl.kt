package com.example.githubclone.data.repositoryimpl

import com.example.githubclone.data.apiservice.GithubApi
import com.example.githubclone.domain.repository.RepositoriesRepository
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RepositoriesRepositoryImpl(private val api: GithubApi): RepositoriesRepository {

    override suspend fun getUserRepositories() = flow {
        emit(ResultData.Loading)

        val response = api.getUserRepositories()
        val result = response.body()
        if (response.isSuccessful){
            emit(ResultData.Success(result))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }.flowOn(Dispatchers.IO)
}