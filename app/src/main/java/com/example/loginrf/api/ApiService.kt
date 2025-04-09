package com.example.loginrf.api

import com.example.loginrf.data.model.LoginRequest
import com.example.loginrf.data.model.LoginResponse
import com.example.loginrf.data.model.RegisterRequest
import com.example.loginrf.data.model.RegisterResponse
import com.example.loginrf.data.model.UserResponse
import com.example.loginrf.data.model.UsersWrapper
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @GET("users")
    suspend fun getUsers(): Response<UsersWrapper>

}