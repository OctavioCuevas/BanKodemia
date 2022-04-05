package com.honeykoders.bankodemia.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.honeykoders.bankodemia.ui.model.UserProfileResponse
import com.honeykoders.bankodemia.network.ServiceNetwork
import kotlinx.coroutines.launch
import java.lang.Exception

class GetUserProfileViewModel : ViewModel() {
    lateinit var service: ServiceNetwork
    val getUserResponse = MutableLiveData<UserProfileResponse>()
    val error = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()

    fun onCreate(context: Context) {
        service = ServiceNetwork(context)
    }

    fun getUserProfile() {
        loading.postValue(true)
        viewModelScope.launch {
            try {
                val response = service.getUserProfile()
                if (response.isSuccessful) {
                    getUserResponse.postValue(response.body())
                }
            } catch (e: Exception) {
                error.postValue(true)
            }
        }
        loading.postValue(false)
    }
}