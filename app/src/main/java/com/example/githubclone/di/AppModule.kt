package com.example.githubclone.di

import com.example.githubclone.presentation.viewmodels.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        LoginViewModel(repo = get())
    }

    viewModel {
        ProfileViewModel(repo = get())
    }

    viewModel {
        RepositoriesViewModel(repo = get())
    }

    viewModel {
        SearchWithUsernameViewModel(repo = get())
    }

    viewModel {
        SearchViewModel(repo = get())
    }

    viewModel {
        SearchWithRepositoriesViewModel(repo = get())
    }
}
