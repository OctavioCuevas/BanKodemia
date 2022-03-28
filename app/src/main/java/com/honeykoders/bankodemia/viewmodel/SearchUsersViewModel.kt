package com.honeykoders.bankodemia.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.honeykoders.bankodemia.model.*
import com.honeykoders.bankodemia.network.ServiceNetwork
import kotlinx.coroutines.launch

class SearchUsersViewModel:ViewModel() {
    val service = ServiceNetwork()
    val searchUsersResponse = MutableLiveData<SearchUsersModel>()
    val loading = MutableLiveData<Boolean>()

    fun loginUser(query: String){
        loading.postValue(true)
        viewModelScope.launch {
            val respuesta = service.searchUser(query)
            Log.e("codigo", respuesta.raw().toString())
            if (respuesta.isSuccessful){
                searchUsersResponse.postValue(respuesta.body())
                Log.e("Success ",respuesta.body().toString())
            }
        }
        loading.postValue(false)
    }
}