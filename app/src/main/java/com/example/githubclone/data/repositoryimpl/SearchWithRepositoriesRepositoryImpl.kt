package com.example.githubclone.data.repositoryimpl

import com.example.githubclone.data.apiservice.GithubApi
import com.example.githubclone.domain.repository.SearchWithRepositoriesRepository
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchWithRepositoriesRepositoryImpl(private val api: GithubApi): SearchWithRepositoriesRepository {
    override suspend fun searchWithRepositories(name: String) = flow {
        val response = api.searchRepositoriesByName(name)
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