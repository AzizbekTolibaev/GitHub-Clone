package com.example.githubclone.utils

import okhttp3.Interceptor

class AccessTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain) = chain.proceed(
        chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "Bearer ${shPreferenceGetString("userToken", "asses_key")}"
            ).build()
    )
}
