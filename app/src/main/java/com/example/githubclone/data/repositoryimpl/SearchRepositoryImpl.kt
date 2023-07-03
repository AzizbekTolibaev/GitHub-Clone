package com.example.githubclone.data.repositoryimpl

import com.example.githubclone.data.dao.SearchDao
import com.example.githubclone.data.dao.entity.SearchData
import com.example.githubclone.domain.repository.SearchRepository

class SearchRepositoryImpl(private val dao: SearchDao): SearchRepository {
    override suspend fun getAllSearch() = dao.getAllSearch()

    override suspend fun addSearch(searchData: SearchData) = dao.addSearch(searchData)

    override suspend fun deleteSearch(searchData: SearchData) = dao.deleteSearch(searchData)
}