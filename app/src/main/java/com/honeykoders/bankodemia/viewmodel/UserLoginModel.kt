package com.honeykoders.bankodemia.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.honeykoders.bankodemia.model.LoginModel
import com.honeykoders.bankodemia.model.ResponseUserLoggedIn
import com.honeykoders.bankodemia.model.ErrorResponse
import com.honeykoders.bankodemia.model.ErrorResponseBadRequest
import com.honeykoders.bankodemia.network.ServiceNetwork
import kotlinx.coroutines.launch

class UserLoginModel:ViewModel() {
    lateinit var service: ServiceNetwork
    val loginResponse = MutableLiveData<ResponseUserLoggedIn>()
    val badRequest = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun onCreate(context: Context){
        service = ServiceNetwork(context)
    }

    fun loginUser(login: LoginModel){
        loading.postValue(true)
        viewModelScope.launch {
            val respuesta = service.loginUser(login)
            Log.e("codigo", respuesta.raw().toString())
            if (respuesta.isSuccessful){
                loginResponse.postValue(respuesta.body())
                Log.e("Success ",respuesta.body().toString())
            }else{
                if(respuesta.code() == 400){
                    val gson = Gson()
                    val type = object : TypeToken<ErrorResponseBadRequest>() {}.type
                    var errorResponse: ErrorResponse? = gson.fromJson(respuesta.errorBody()!!.charStream(), type)
                    Log.e("Response400",errorResponse.toString())
                    badRequest.postValue(true)
                }else{
                    if(respuesta.code() == 401){
                        val gson = Gson()
                        val type = object : TypeToken<ErrorResponse>() {}.type
                        var errorResponse: ErrorResponse? = gson.fromJson(respuesta.errorBody()!!.charStream(), type)
                        Log.e("Response401",errorResponse.toString())
                        error.postValue(errorResponse?.message)
                    }
                }
            }
        }
        loading.postValue(false)
    }
}