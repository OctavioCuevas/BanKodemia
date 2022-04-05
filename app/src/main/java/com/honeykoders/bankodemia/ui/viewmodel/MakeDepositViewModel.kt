package com.honeykoders.bankodemia.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.honeykoders.bankodemia.model.*
import com.honeykoders.bankodemia.network.ServiceNetwork
import com.honeykoders.bankodemia.ui.model.ErrorResponse
import com.honeykoders.bankodemia.ui.model.MakeTransactionDeposit
import kotlinx.coroutines.launch
import java.io.IOException

class MakeDepositViewModel : ViewModel() {
    lateinit var service: ServiceNetwork
    val makeDepositResponse = MutableLiveData<ResponseTransactionCreatedDeposit>()
    val badRequest = MutableLiveData<Boolean>()
    val broken = MutableLiveData<Boolean>()
    val insufficientFunds = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun onCreate(context: Context) {
        service = ServiceNetwork(context)
    }

    fun makeTransactionDeposit(makeTransactionDeposi: MakeTransactionDeposit) {
        loading.postValue(true)
        try {
            viewModelScope.launch {
                val response = service.makeTransactionDeposit(makeTransactionDeposi)
                if (response.isSuccessful) {
                    makeDepositResponse.postValue(response.body())
                } else {
                    val gson = Gson()
                    val type = object : TypeToken<ErrorResponse>() {}.type
                    var errorResponse: ErrorResponse? =
                        gson.fromJson(response.errorBody()!!.charStream(), type)
                    if (errorResponse != null) {
                        if (errorResponse.statusCode == 401) {
                            error.postValue(errorResponse.message[0].toString())
                            badRequest.postValue(true)
                        } else {
                            if (response.code() == 402) {
                                error.postValue(errorResponse.message[0].toString())
                                broken.postValue(true)
                            } else {
                                if (response.code() == 412) {
                                    error.postValue(errorResponse.message[0].toString())
                                    insufficientFunds.postValue(true)
                                }
                            }
                        }
                    }
                }
            }
            loading.postValue(false)
        } catch (e: IOException) {
            Log.e("Response", e.toString())
        }
    }
}