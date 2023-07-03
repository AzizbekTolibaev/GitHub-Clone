package com.example.githubclone.di

import com.example.githubclone.data.repositoryimpl.*
import org.koin.dsl.module
import kotlin.math.sin

val dataModule = module {
    single {
        LoginRepositoryImpl(api = get())
    }

    single {
        ProfileRepositoryImpl(api = get())
    }

    single {
        RepositoriesRepositoryImpl(api = get())
    }

    single {
        SearchWithUsernameRepositoryImpl(api = get())
    }

    single {
        SearchRepositoryImpl(dao = get())
    }

    single {
        SearchWithRepositoriesRepositoryImpl(api = get())
    }
}