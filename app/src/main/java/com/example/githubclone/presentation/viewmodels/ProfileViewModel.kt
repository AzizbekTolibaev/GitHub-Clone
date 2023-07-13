package com.example.githubclone.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclone.data.GetUserProfileInfoData
import com.example.githubclone.data.RepositoryResponseData
import com.example.githubclone.data.repositoryimpl.ProfileRepositoryImpl
import com.example.githubclone.domain.usecase.impl.UseCaseUserRepositoriesToProfileImpl
import com.example.githubclone.domain.usecase.impl.UseCaseUserProfileInfoImpl
import com.example.githubclone.utils.ResultData
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ProfileViewModel(private val repo: ProfileRepositoryImpl): ViewModel() {

    private val _userProfileInfoLoadingLiveData = MutableLiveData<Boolean>()
    val userProfileInfoLoadingLiveData: LiveData<Boolean> get() = _userProfileInfoLoadingLiveData

    private val _userProfileInfoSuccessLiveData = MutableLiveData<GetUserProfileInfoData?>()
    val userProfileInfoSuccessLiveData: LiveData<GetUserProfileInfoData?> get() = _userProfileInfoSuccessLiveData

    private val _userProfileInfoMessageLiveData = MutableLiveData<String>()
    val userProfileInfoMessageLiveData: LiveData<String> get() = _userProfileInfoMessageLiveData

    private val _userProfileInfoErrorLiveData = MutableLiveData<Throwable>()
    val userProfileInfoErrorLiveData: LiveData<Throwable> get() = _userProfileInfoErrorLiveData

    suspend fun getUserProfileInfo() {
        val useCase = UseCaseUserProfileInfoImpl(repo)

        useCase.execute().onEach {
            when (it) {
                is ResultData.Loading -> {
                    _userProfileInfoLoadingLiveData.value = true
                }
                is ResultData.Success -> {
                    _userProfileInfoSuccessLiveData.value = it.data
                    _userProfileInfoLoadingLiveData.value = false
                }
                is ResultData.Message -> {
                    _userProfileInfoMessageLiveData.value = it.message
                    _userProfileInfoLoadingLiveData.value = false
                }
                is ResultData.Error -> {
                    _userProfileInfoErrorLiveData.value = it.error
                    _userProfileInfoLoadingLiveData.value = false
                }
            }
        }.launchIn(viewModelScope)
    }

    // Repositories
    private val _reposSuccessLiveData = MutableLiveData<MutableList<RepositoryResponseData>?>()
    val reposSuccessLiveData: LiveData<MutableList<RepositoryResponseData>?> get() = _reposSuccessLiveData

    private val _reposMessageLiveData = MutableLiveData<String>()
    val reposMessageLiveData: LiveData<String> get() = _reposMessageLiveData

    private val _reposErrorLiveData = MutableLiveData<Throwable>()
    val reposErrorLiveData: LiveData<Throwable> get() = _reposErrorLiveData

    suspend fun getUserRepositories() {
        val useCase = UseCaseUserRepositoriesToProfileImpl(repo)

        useCase.execute().onEach {
            when (it) {
                is ResultData.Success -> {
                    _reposSuccessLiveData.value = it.data
                }
                is ResultData.Message -> {
                    _reposMessageLiveData.value = it.message
                }
                is ResultData.Error -> {
                    _reposErrorLiveData.value = it.error
                }
                else -> { }
            }
        }.launchIn(viewModelScope)
    }
}
