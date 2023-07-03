package com.example.githubclone.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclone.data.RepositoryResponseData
import com.example.githubclone.data.repositoryimpl.RepositoriesRepositoryImpl
import com.example.githubclone.domain.usecase.UseCaseRepositories
import com.example.githubclone.domain.usecase.impl.UseCaseRepositoriesImpl
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RepositoriesViewModel(private val repo: RepositoriesRepositoryImpl): ViewModel() {

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _successLiveData = MutableLiveData<MutableList<RepositoryResponseData>?>()
    val successLiveData: LiveData<MutableList<RepositoryResponseData>?> get() = _successLiveData

    private val _messageLiveData = MutableLiveData<String>()
    val messageLiveData: LiveData<String> get() = _messageLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> get() = _errorLiveData

    suspend fun getUserRepositories() {
        val useCase = UseCaseRepositoriesImpl(repo)
        useCase.execute().onEach {
            when (it) {
                is ResultData.Loading -> {
                    _loadingLiveData.value = true
                }
                is ResultData.Success -> {
                    _successLiveData.value = it.data
                    _loadingLiveData.value = false
                }
                is ResultData.Message -> {
                    _messageLiveData.value = it.message
                    _loadingLiveData.value = false
                }
                is ResultData.Error -> {
                    _errorLiveData.value = it.error
                    _loadingLiveData.value = false
                }
            }
        }.launchIn(viewModelScope)
    }
}
