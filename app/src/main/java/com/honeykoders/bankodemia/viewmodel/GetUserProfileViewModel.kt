package com.honeykoders.bankodemia.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.honeykoders.bankodemia.model.SearchUsersModel
import com.honeykoders.bankodemia.model.UserProfileResponse
import com.honeykoders.bankodemia.network.ServiceNetwork
import kotlinx.coroutines.launch
import java.lang.Exception

class GetUserProfileViewModel:ViewModel() {
    lateinit var service: ServiceNetwork
    val getUserResponse = MutableLiveData<UserProfileResponse>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun onCreate(context: Context){
        service = ServiceNetwork(context)
    }

    fun getUserProfile(){
        loading.postValue(true)
        viewModelScope.launch {
            try {
                val respuesta = service.getUserProfile()
                Log.e("respuestaGet", respuesta.raw().toString())
                if (respuesta.isSuccessful) {
                    getUserResponse.postValue(respuesta.body())
                    Log.e("Success Get ", respuesta.body().toString())
                }
            }catch (e: Exception){
                Log.e("Error Get ", e.toString())
                error.postValue(true)
            }
        }
        loading.postValue(false)
    }
}