package com.example.githubclone.domain.usecase.searchfragmentroomusecases.impl

import com.example.githubclone.data.dao.entity.SearchData
import com.example.githubclone.domain.repository.SearchRepository
import com.example.githubclone.domain.usecase.searchfragmentroomusecases.UseCaseAddSearch

class UseCaseAddSearchImpl(private val repo: SearchRepository): UseCaseAddSearch {
    override suspend fun execute(searchData: SearchData) {
        repo.addSearch(searchData)
    }
}