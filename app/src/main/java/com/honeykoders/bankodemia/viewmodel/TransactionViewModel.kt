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
import java.io.IOException

class TransactionViewModel:ViewModel() {
    val service = ServiceNetwork()
    val makeTransactionResponse = MutableLiveData<ResponseTransactionCreated>()
    val badRequest = MutableLiveData<Boolean>()
    val broken = MutableLiveData<Boolean>()
    val inssuficientFunds = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun makeTransactionPayment(makeTransactionPayment: MakeTransactionPayment){
           viewModelScope.launch {
               loading.postValue(true)
               val respuesta = service.makeTransactionPayment(makeTransactionPayment)
               Log.e("codigo", respuesta.raw().toString())
               try {
                    if (respuesta.isSuccessful) {
                        Log.e("codigo", respuesta.body().toString())
                        makeTransactionResponse.postValue(respuesta.body())
                    }else{
                        val gson = Gson()
                        val type = object : TypeToken<SingUpErrorResponse>() {}.type
                        var errorResponse: SingUpErrorResponse? = gson.fromJson(respuesta.errorBody()!!.charStream(), type)
                        Log.e("Response", errorResponse.toString())
                        if (errorResponse != null) {
                            if (errorResponse.statusCode == 401) {//badRequest
                                error.postValue(errorResponse.message[0].toString())
                                badRequest.postValue(true)
                            }else{
                                if(respuesta.code() == 402){ //Yo are broke
                                    error.postValue(errorResponse.message[0].toString())
                                    broken.postValue(true)
                                }else{
                                    if(respuesta.code() == 412){// inssuficientFunds
                                        error.postValue(errorResponse.message[0].toString())
                                        inssuficientFunds.postValue(true)
                                    }
                                }
                            }
                        }
                    }
                   loading.postValue(false)
               }catch(e: IOException){
                   Log.e("Response", e.toString())
               }
           }
    }
}