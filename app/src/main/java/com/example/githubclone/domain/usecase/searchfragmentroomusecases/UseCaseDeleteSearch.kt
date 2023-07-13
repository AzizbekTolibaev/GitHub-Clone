package com.example.githubclone.domain.usecase.searchfragmentroomusecases

import com.example.githubclone.data.dao.entity.SearchData

interface UseCaseDeleteSearch {

    suspend fun execute(searchData: SearchData)
}