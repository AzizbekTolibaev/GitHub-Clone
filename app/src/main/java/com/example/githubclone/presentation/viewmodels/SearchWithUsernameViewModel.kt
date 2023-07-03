package com.example.githubclone.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclone.data.SearchWithUsernameResponseData
import com.example.githubclone.data.repositoryimpl.SearchWithUsernameRepositoryImpl
import com.example.githubclone.domain.usecase.impl.UseCaseSearchWithUsernameImpl
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchWithUsernameViewModel(private val repo: SearchWithUsernameRepositoryImpl): ViewModel() {

    private val _successLiveData = MutableLiveData<SearchWithUsernameResponseData?>()
    val successLiveData: LiveData<SearchWithUsernameResponseData?> get() = _successLiveData

    private val _messageLiveData = MutableLiveData<String>()
    val messageLiveData: LiveData<String> get() = _messageLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> get() = _errorLiveData

    suspend fun searchWithUsername(userName: String) {
        val useCase = UseCaseSearchWithUsernameImpl(repo)

        useCase.execute(userName).onEach {
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