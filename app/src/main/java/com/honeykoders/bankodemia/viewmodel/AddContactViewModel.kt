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
import com.honeykoders.bankodemia.view.AddNewContact
import kotlinx.coroutines.launch
import java.io.IOException

class AddContactViewModel:ViewModel() {
    lateinit var service: ServiceNetwork
    val addNewContactResponse = MutableLiveData<ContactAdded>()
    val badRequest = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun onCreate(context: Context){
        service = ServiceNetwork(context)
    }

    fun addNewContact(newContact: AddNewContactModel){
        loading.postValue(true)
            viewModelScope.launch {
                try {
                    val respuesta = service.addNewContact(newContact)
                    Log.e("codigo", respuesta.raw().toString())
                    if (respuesta.isSuccessful) {
                        addNewContactResponse.postValue(respuesta.body())
                        Log.e("Success ", respuesta.body().toString())
                    }
                }catch(e: IOException){
                    Log.e("Response", e.toString())
                }
            }
        loading.postValue(true)
    }
}