package com.example.githubclone.domain.repository

import com.example.githubclone.data.dao.entity.SearchData

interface SearchRepository {

    suspend fun getAllSearch(): MutableList<SearchData>

    suspend fun addSearch(searchData: SearchData)

    suspend fun deleteSearch(searchData: SearchData)
}