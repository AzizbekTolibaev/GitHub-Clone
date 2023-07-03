package com.example.githubclone.domain.usecase.searchfragmentusecases

import com.example.githubclone.data.dao.entity.SearchData

interface UseCaseGetAllSearch {

    suspend fun execute(): MutableList<SearchData>

}