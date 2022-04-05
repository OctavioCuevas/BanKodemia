package com.honeykoders.bankodemia.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.honeykoders.bankodemia.ui.model.*
import com.honeykoders.bankodemia.network.ServiceNetwork
import kotlinx.coroutines.launch
import java.io.IOException

class AddContactViewModel : ViewModel() {
    lateinit var service: ServiceNetwork
    val addNewContactResponse = MutableLiveData<ContactAdded>()
    val badRequest = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()

    fun onCreate(context: Context) {
        service = ServiceNetwork(context)
    }

    fun addNewContact(newContact: AddNewContactModel) {
        loading.postValue(true)
        viewModelScope.launch {
            try {
                val response = service.addNewContact(newContact)
                if (response.isSuccessful) {
                    addNewContactResponse.postValue(response.body())
                }
            } catch (e: IOException) {
                Log.e("Response", e.toString())
            }
        }
        loading.postValue(true)
    }
}