package com.example.loginrf.ui.auth
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.loginrf.data.model.LoginResponse
import com.example.loginrf.data.model.RegisterResponse
import com.example.loginrf.data.repository.ApiRepository
import com.example.loginrf.data.model.UserResponse
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

    private val _usersResult = MutableLiveData<Result<List<UserResponse>>>()
    val usersResult: LiveData<Result<List<UserResponse>>> get() = _usersResult

    fun getUsers() {
        viewModelScope.launch {
            val result = repository.getUsers()
            _usersResult.postValue(result)
        }
    }

    private val _userResult = MutableLiveData<Result<UserResponse>>()
    val userResult: LiveData<Result<UserResponse>> get() = _userResult

    fun getUserById(userId: Int) {
        viewModelScope.launch {
            val result = repository.getUserById(userId)
            _userResult.postValue(result)
        }
    }

}
