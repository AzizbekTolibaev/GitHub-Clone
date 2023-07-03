package com.example.githubclone.domain.usecase.searchfragmentusecases.impl

import com.example.githubclone.data.dao.entity.SearchData
import com.example.githubclone.domain.repository.SearchRepository
import com.example.githubclone.domain.usecase.searchfragmentusecases.UseCaseAddSearch

class UseCaseAddSearchImpl(private val repo: SearchRepository): UseCaseAddSearch {
    override suspend fun execute(searchData: SearchData) {
        repo.addSearch(searchData)
    }
}