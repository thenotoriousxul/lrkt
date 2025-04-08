package com.example.loginrf.data.repository
import android.content.Context
import com.example.loginrf.data.model.LoginRequest
import com.example.loginrf.data.model.LoginResponse
import com.example.loginrf.data.model.RegisterRequest
import com.example.loginrf.data.model.RegisterResponse
import com.example.loginrf.api.RetrofitClient
import com.example.loginrf.data.SessionManager


class ApiRepository(private val context: Context) {
    private val retrofitClient = RetrofitClient(context)
    private val apiService = retrofitClient.apiService
    private val sessionManager = SessionManager(context)

    suspend fun login(email: String, password: String): Result<LoginResponse> {
        return try {
            val response = apiService.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                response.body()?.let { loginResponse ->
                    sessionManager.saveToken(loginResponse.access_token)
                    Result.success(loginResponse)
                } ?: Result.failure(Exception("Respuesta vacía"))
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(name: String, email: String, password: String): Result<RegisterResponse> {
        return try {
            val response = apiService.register(RegisterRequest(name, email, password))
            if (response.isSuccessful) {
                response.body()?.let { registerResponse ->
                    Result.success(registerResponse)
                } ?: Result.failure(Exception("Respuesta vacía"))
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}