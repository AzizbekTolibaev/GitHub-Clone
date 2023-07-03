package com.example.githubclone.domain.usecase.searchfragmentusecases.impl

import com.example.githubclone.data.dao.entity.SearchData
import com.example.githubclone.domain.repository.SearchRepository
import com.example.githubclone.domain.usecase.searchfragmentusecases.UseCaseDeleteSearch

class UseCaseDeleteSearchImpl(private val repo: SearchRepository): UseCaseDeleteSearch {
    override suspend fun execute(searchData: SearchData) {
        repo.deleteSearch(searchData)
    }
}