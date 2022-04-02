package com.honeykoders.bankodemia.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.honeykoders.bankodemia.ui.model.*
import com.honeykoders.bankodemia.network.ServiceNetwork
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchUsersViewModel:ViewModel() {
    lateinit var service: ServiceNetwork
    val searchUsersResponse = MutableLiveData<SearchUsersModel>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun onCreate(context: Context){
        service = ServiceNetwork(context)
    }

    fun searchUser(query: String){
        loading.postValue(true)
            viewModelScope.launch {
                try {
                    val respuesta = service.searchUser(query)
                    Log.e("codigo", respuesta.raw().toString())
                    if (respuesta.isSuccessful) {
                        searchUsersResponse.postValue(respuesta.body())
                        Log.e("Success ", respuesta.body().toString())
                    }
                }catch (e: Exception){
                    error.postValue(true)
                }
            }
        loading.postValue(false)
    }
}