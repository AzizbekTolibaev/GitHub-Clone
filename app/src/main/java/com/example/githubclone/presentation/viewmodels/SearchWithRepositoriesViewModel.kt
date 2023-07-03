package com.example.githubclone.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclone.data.SearchWithRepositoriesResponseData
import com.example.githubclone.data.repositoryimpl.SearchWithRepositoriesRepositoryImpl
import com.example.githubclone.domain.usecase.impl.UseCaseSearchWithRepositoriesImpl
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchWithRepositoriesViewModel(private val repo: SearchWithRepositoriesRepositoryImpl): ViewModel() {

    private val _successLiveData = MutableLiveData<SearchWithRepositoriesResponseData?>()
    val successLiveData: LiveData<SearchWithRepositoriesResponseData?> get() = _successLiveData

    private val _messageLiveData = MutableLiveData<String>()
    val messageLiveData: LiveData<String> get() = _messageLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> get() = _errorLiveData

    suspend fun searchWithRepositories(name: String) {
        val useCase = UseCaseSearchWithRepositoriesImpl(repo)
        useCase.execute(name).onEach {
            when (it) {
                is ResultData.Success -> {
                    _successLiveData.value = it.data
                }
                is ResultData.Message -> {
                    _messageLiveData.value = it.message
                }
                is ResultData.Error -> {
                    _errorLiveData.value = it.error
                }
                else -> { }
            }
        }.launchIn(viewModelScope)
    }
}