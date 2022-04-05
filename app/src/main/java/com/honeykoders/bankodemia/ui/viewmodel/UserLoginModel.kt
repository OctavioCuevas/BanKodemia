package com.honeykoders.bankodemia.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.honeykoders.bankodemia.ui.model.LoginModel
import com.honeykoders.bankodemia.ui.model.ResponseUserLoggedIn
import com.honeykoders.bankodemia.ui.model.ErrorResponse
import com.honeykoders.bankodemia.ui.model.ErrorResponseBadRequest
import com.honeykoders.bankodemia.network.ServiceNetwork
import kotlinx.coroutines.launch

class UserLoginModel : ViewModel() {
    lateinit var service: ServiceNetwork
    val loginResponse = MutableLiveData<ResponseUserLoggedIn>()
    val badRequest = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()

    fun onCreate(context: Context) {
        service = ServiceNetwork(context)
    }

    fun loginUser(login: LoginModel) {
        loading.postValue(true)
        viewModelScope.launch {
            val response = service.loginUser(login)
            if (response.isSuccessful) {
                loginResponse.postValue(response.body())
            } else {
                if (response.code() == 400) {
                    val gson = Gson()
                    val type = object : TypeToken<ErrorResponseBadRequest>() {}.type
                    val errorResponse: ErrorResponse? =
                        gson.fromJson(response.errorBody()!!.charStream(), type)
                    badRequest.postValue(true)
                } else {
                    if (response.code() == 401) {
                        val gson = Gson()
                        val type = object : TypeToken<ErrorResponse>() {}.type
                        val errorResponse: ErrorResponse? =
                            gson.fromJson(response.errorBody()!!.charStream(), type)
                        error.postValue(errorResponse?.message)
                    }
                }
            }
        }
        loading.postValue(false)
    }
}