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

class TransactionViewModel : ViewModel() {
    lateinit var service: ServiceNetwork
    val makeTransactionResponse = MutableLiveData<ResponseTransactionCreated>()
    val badRequest = MutableLiveData<Boolean>()
    val broken = MutableLiveData<Boolean>()
    val insufficientFunds = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun onCreate(context: Context) {
        service = ServiceNetwork(context)
    }

    fun makeTransactionPayment(makeTransactionPayment: MakeTransactionPayment) {
        loading.postValue(true)
        try {
            viewModelScope.launch {
                val response = service.makeTransactionPayment(makeTransactionPayment)
                if (response.isSuccessful) {
                    makeTransactionResponse.postValue(response.body())
                } else {
                    val gson = Gson()
                    val type = object : TypeToken<ErrorResponse>() {}.type
                    val errorResponse: ErrorResponse? =
                        gson.fromJson(response.errorBody()!!.charStream(), type)
                    if (errorResponse != null) {
                        if (errorResponse.statusCode == 401) {//badRequest
                            error.postValue(errorResponse.message[0].toString())
                            badRequest.postValue(true)
                        } else {
                            if (response.code() == 402) { //Yo are broke
                                error.postValue(errorResponse.message[0].toString())
                                broken.postValue(true)
                            } else {
                                if (response.code() == 412) {// insufficientFunds
                                    error.postValue(errorResponse.message[0].toString())
                                    insufficientFunds.postValue(true)
                                }
                            }
                        }
                    }
                }
            }
            loading.postValue(true)
        } catch (e: IOException) {
            Log.e("Response", e.toString())
        }
    }
}