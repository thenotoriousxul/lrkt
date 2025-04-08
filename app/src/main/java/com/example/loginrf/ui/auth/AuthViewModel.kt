package com.example.loginrf.ui.auth
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.loginrf.data.model.LoginResponse
import com.example.loginrf.data.model.RegisterResponse
import com.example.loginrf.data.repository.ApiRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope


class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ApiRepository(application)

    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> get() = _loginResult

    private val _registerResult = MutableLiveData<Result<RegisterResponse>>()
    val registerResult: LiveData<Result<RegisterResponse>> get() = _registerResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.login(email, password)
            _loginResult.postValue(result)
        }
    }

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            val result = repository.register(name, email, password)
            _registerResult.postValue(result)
        }
    }
}