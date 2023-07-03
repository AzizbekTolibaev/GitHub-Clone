package com.example.githubclone.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubclone.data.dao.entity.SearchData
import com.example.githubclone.data.repositoryimpl.SearchRepositoryImpl
import com.example.githubclone.domain.usecase.searchfragmentusecases.UseCaseAddSearch
import com.example.githubclone.domain.usecase.searchfragmentusecases.UseCaseDeleteSearch
import com.example.githubclone.domain.usecase.searchfragmentusecases.impl.UseCaseAddSearchImpl
import com.example.githubclone.domain.usecase.searchfragmentusecases.impl.UseCaseDeleteSearchImpl
import com.example.githubclone.domain.usecase.searchfragmentusecases.impl.UseCaseGetAllSearchImpl

class SearchViewModel(private val repo: SearchRepositoryImpl): ViewModel() {

    private val _getAllSearchLiveData = MutableLiveData<MutableList<SearchData>>()
    val getAllSearchLiveData: LiveData<MutableList<SearchData>> get() = _getAllSearchLiveData

    suspend fun getAllSearch() {
        val useCase = UseCaseGetAllSearchImpl(repo)

        _getAllSearchLiveData.value = useCase.execute()
    }

    suspend fun addSearch(searchData: SearchData) {
        val useCase = UseCaseAddSearchImpl(repo)

        useCase.execute(searchData)
    }

    suspend fun delete(searchData: SearchData) {
        val useCase = UseCaseDeleteSearchImpl(repo)

        useCase.execute(searchData)
    }
}