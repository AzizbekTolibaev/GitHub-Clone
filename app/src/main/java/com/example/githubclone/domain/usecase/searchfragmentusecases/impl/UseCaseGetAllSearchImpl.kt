package com.example.githubclone.domain.usecase.searchfragmentusecases.impl

import com.example.githubclone.data.dao.entity.SearchData
import com.example.githubclone.domain.repository.SearchRepository
import com.example.githubclone.domain.usecase.searchfragmentusecases.UseCaseGetAllSearch

class UseCaseGetAllSearchImpl(private val repo: SearchRepository): UseCaseGetAllSearch {
    override suspend fun execute(): MutableList<SearchData> {
        return repo.getAllSearch()
    }
}