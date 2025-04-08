package com.example.loginrf.api

import com.example.loginrf.data.model.LoginRequest
import com.example.loginrf.data.model.LoginResponse
import com.example.loginrf.data.model.RegisterRequest
import com.example.loginrf.data.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
}