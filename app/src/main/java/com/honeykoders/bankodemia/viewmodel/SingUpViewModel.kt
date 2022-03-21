package com.honeykoders.bankodemia.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.honeykoders.bankodemia.model.ResponseSingUp
import com.honeykoders.bankodemia.model.SingUpErrorResponse
import com.honeykoders.bankodemia.model.SingUpModel
import com.honeykoders.bankodemia.network.ServiceNetwork
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.security.AccessController.getContext


class SingUpViewModel: ViewModel() {
    val service = ServiceNetwork()
    val singUpResponse = MutableLiveData<ResponseSingUp>()
    val badRequest = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun singUp(singUp: SingUpModel){
        Log.e("error",singUp.toString())
        viewModelScope.launch {
            val respuesta = service.singUp(singUp)
            Log.e("codigo", respuesta.raw().toString())
            if (respuesta.isSuccessful){
                singUpResponse.postValue(respuesta.body())
            }else
                if(respuesta.code() == 400){
                    val gson = Gson()
                    val type = object : TypeToken<SingUpErrorResponse>() {}.type
                    var errorResponse: SingUpErrorResponse? = gson.fromJson(respuesta.errorBody()!!.charStream(), type)
                    Log.e("Response",errorResponse.toString())
                badRequest.postValue(true)
            }
        }
    }

}