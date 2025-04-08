package com.example.loginrf.util
import com.example.loginrf.data.SessionManager
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor(private val sessionManager: SessionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder.addHeader("Accept", "application/json")

        sessionManager.getToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}