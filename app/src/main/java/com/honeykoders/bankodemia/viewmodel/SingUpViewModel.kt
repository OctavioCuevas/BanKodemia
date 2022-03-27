package com.honeykoders.bankodemia.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.honeykoders.bankodemia.model.*
import com.honeykoders.bankodemia.network.ServiceNetwork
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.IOException
import java.security.AccessController.getContext


class SingUpViewModel: ViewModel() {
    val service = ServiceNetwork()
    val singUpResponse = MutableLiveData<ResponseSingUp>()
    val badRequest = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun singUp(singUp: SingUpModel){
        loading.postValue(true)
        try {
        viewModelScope.launch {
            val respuesta = service.singUp(singUp)
            Log.e("codigo", respuesta.raw().toString())
            if (respuesta.isSuccessful){
                singUpResponse.postValue(respuesta.body())
                Log.e("Success ",respuesta.body().toString())
            }else{
                if(respuesta.code() == 400){
                    val gson = Gson()
                    val type = object : TypeToken<SingUpErrorResponse>() {}.type
                    var errorResponse: SingUpErrorResponse? = gson.fromJson(respuesta.errorBody()!!.charStream(), type)
                    Log.e("Response400",errorResponse.toString())
                    badRequest.postValue(true)
                }else{
                    if(respuesta.code() == 412){
                        val gson = Gson()
                        val type = object : TypeToken<PreconditionError>() {}.type
                        var errorResponse: PreconditionError? = gson.fromJson(respuesta.errorBody()!!.charStream(), type)
                        Log.e("Response412",errorResponse.toString())
                        badRequest.postValue(true)
                        error.postValue(errorResponse?.message)
                    }
                }
            }
        }
        loading.postValue(true)
        }catch(e: IOException){
            Log.e("Response", e.toString())
        }
    }
}