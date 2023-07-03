package com.example.githubclone.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclone.data.repositoryimpl.LoginRepositoryImpl
import com.example.githubclone.domain.usecase.UseCaseLogin
import com.example.githubclone.domain.usecase.impl.UseCaseLoginImpl
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginViewModel(private val repo: LoginRepositoryImpl): ViewModel() {

    private val _successLiveData = MutableLiveData<String?>()
    val successLiveData: LiveData<String?> get() = _successLiveData

    private val _messageLiveData = MutableLiveData<String>()
    val messageLiveData: LiveData<String> get() = _messageLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> get() = _errorLiveData

    suspend fun getAccessToken(code: String) {
        val useCase = UseCaseLoginImpl(repo)
        useCase.execute(code).onEach {
            when(it) {
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
