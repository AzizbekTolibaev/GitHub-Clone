package com.example.githubclone.di

import android.content.Context
import com.example.githubclone.App
import com.example.githubclone.data.apiservice.GithubApi
import com.example.githubclone.data.dao.SearchDao
import com.example.githubclone.data.dao.database.SearchDatabase
import com.example.githubclone.utils.AccessTokenInterceptor
import com.example.githubclone.utils.LocalStorage
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<GithubApi> {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(AccessTokenInterceptor( ))
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com")
            .client(okHttpClient)
            .build()

        retrofit.create(GithubApi::class.java)
    }

    single<SearchDao> {
        SearchDatabase.getInstance().getSearchDao()
    }
}