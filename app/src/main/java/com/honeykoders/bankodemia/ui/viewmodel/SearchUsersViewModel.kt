package com.honeykoders.bankodemia.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.honeykoders.bankodemia.ui.model.*
import com.honeykoders.bankodemia.network.ServiceNetwork
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchUsersViewModel : ViewModel() {
    lateinit var service: ServiceNetwork
    val searchUsersResponse = MutableLiveData<SearchUsersModel>()
    val error = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()

    fun onCreate(context: Context) {
        service = ServiceNetwork(context)
    }

    fun searchUser(query: String) {
        loading.postValue(true)
        viewModelScope.launch {
            try {
                val response = service.searchUser(query)
                if (response.isSuccessful) {
                    searchUsersResponse.postValue(response.body())
                }
            } catch (e: Exception) {
                error.postValue(true)
            }
        }
        loading.postValue(false)
    }
}