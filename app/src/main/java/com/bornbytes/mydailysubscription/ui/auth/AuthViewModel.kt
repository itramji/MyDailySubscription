package com.bornbytes.mydailysubscription.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bornbytes.mydailysubscription.api.RemoteResult
import com.bornbytes.mydailysubscription.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(val authRepository: AuthRepository) : ViewModel() {

    val loginResponse = MutableLiveData<RemoteResult<Any>>()

    fun loginUser() = viewModelScope.launch {
        loginResponse.value =  authRepository.loginUser()
    }
}