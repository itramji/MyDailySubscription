package com.bornbytes.mydailysubscription.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bornbytes.mydailysubscription.repository.AuthRepository
import com.bornbytes.mydailysubscription.repository.BaseRepository
import com.bornbytes.mydailysubscription.ui.auth.AuthViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val baseRepository: BaseRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(baseRepository as AuthRepository) as T
            else -> throw IllegalArgumentException("ViewModel not valid")
        }
    }
}