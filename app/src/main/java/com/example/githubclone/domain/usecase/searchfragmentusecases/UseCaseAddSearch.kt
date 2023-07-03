package com.example.githubclone.domain.usecase.searchfragmentusecases

import com.example.githubclone.data.dao.entity.SearchData

interface UseCaseAddSearch {

    suspend fun execute(searchData: SearchData)
}