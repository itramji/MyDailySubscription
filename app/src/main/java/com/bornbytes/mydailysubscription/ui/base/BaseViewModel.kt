package com.bornbytes.mydailysubscription.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bornbytes.mydailysubscription.api.BaseApi
import com.bornbytes.mydailysubscription.repository.BaseRepository
import kotlinx.coroutines.launch

open class BaseViewModel(val baseRepository: BaseRepository) : ViewModel() {

    fun logoutUser(api: BaseApi) = viewModelScope.launch {
        baseRepository.logoutUser(api)
    }
}