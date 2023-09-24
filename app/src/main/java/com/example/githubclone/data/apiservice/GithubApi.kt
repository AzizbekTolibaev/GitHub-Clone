package com.example.githubclone.data.apiservice

import com.example.githubclone.data.*
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface GithubApi {

    @Headers("Accept: application/json")
    @POST("https://github.com/login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String
    ): Response<AccessTokenData>

    @GET("/user")
    suspend fun getUserProfileInfo(): Response<GetUserProfileInfoData>

    @GET("/user/repos")
    suspend fun getUserRepositories(): Response<MutableList<RepositoryResponseData>>

    @GET("/search/users")
    suspend fun searchUsersByUserName(@Query("q") username: String): Response<SearchWithUsernameResponseData>

    @GET("/search/repositories")
    suspend fun searchRepositoriesByName(@Query("q") name: String): Response<SearchWithRepositoriesResponseData>
}

//lfkfkeaklfkssdlfkmksdf