package com.honeykoders.bankodemia.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.honeykoders.bankodemia.ui.model.*
import com.honeykoders.bankodemia.network.ServiceNetwork
import kotlinx.coroutines.launch
import java.io.IOException


class SingUpViewModel : ViewModel() {
    lateinit var service: ServiceNetwork
    val singUpResponse = MutableLiveData<ResponseSingUp>()
    val badRequest = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun onCreate(context: Context) {
        service = ServiceNetwork(context)
    }

    fun singUp(singUp: SingUpModel) {
        loading.postValue(true)
        viewModelScope.launch {
            try {
                val response = service.singUp(singUp)
                if (response.isSuccessful) {
                    singUpResponse.postValue(response.body())
                } else {
                    if (response.code() == 400) {
                        badRequest.postValue(true)
                        val gson = Gson()
                        val type = object : TypeToken<ErrorResponseBadRequest>() {}.type
                        var errorResponse: ErrorResponseBadRequest? =
                            gson.fromJson(response.errorBody()!!.charStream(), type)
                        error.postValue(errorResponse?.message?.get(0))
                    } else {
                        if (response.code() == 412) {
                            val gson = Gson()
                            val type = object : TypeToken<PreconditionError>() {}.type
                            var errorResponse: PreconditionError? =
                                gson.fromJson(response.errorBody()!!.charStream(), type)
                            error.postValue(errorResponse?.message)
                        }
                    }
                }
            } catch (e: IOException) {
                Log.e("Response", e.toString())
            }
        }
        loading.postValue(true)
    }
}