package com.bornbytes.mydailysubscription.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bornbytes.mydailysubscription.api.RemoteResult
import com.bornbytes.mydailysubscription.repository.AuthRepository
import com.bornbytes.mydailysubscription.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel(val authRepository: AuthRepository) : BaseViewModel(authRepository) {

    val loginResponse = MutableLiveData<RemoteResult<Any>>()

    fun loginUser() = viewModelScope.launch {
        loginResponse.value = authRepository.loginUser()
    }
}