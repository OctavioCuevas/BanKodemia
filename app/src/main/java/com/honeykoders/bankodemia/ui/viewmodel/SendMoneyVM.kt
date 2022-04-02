package com.honeykoders.bankodemia.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.honeykoders.bankodemia.exceptions.EmptyTokenException
import com.honeykoders.bankodemia.exceptions.NoContactsFoundException
import com.honeykoders.bankodemia.ui.model.contacts.ContactsMain
import com.honeykoders.bankodemia.network.models.GetContacts
import kotlinx.coroutines.launch
import java.lang.Exception

class SendMoneyVM() : ViewModel() {
    lateinit var serviceGetContacts: GetContacts
    lateinit var context :Context
    //LiveData
    val contactsResponse = MutableLiveData<ContactsMain>()

    fun onCreateVM() {
        serviceGetContacts = GetContacts(context)
    }

    fun getUserContacts() {
        this.viewModelScope.launch {
            try {
                val response = serviceGetContacts.getContactsService()

                Log.e("ContactErrorLog", "Response: ${response}")
                if (response.isSuccessful) {
                    Log.e("ContactErrorLog", "Response: ${response.body()}")
                    contactsResponse.postValue(response.body())
                } else {
                    throw NoContactsFoundException()
                }
            } catch (ex: Exception) {
                when (ex) {
                    is EmptyTokenException ->
                        Log.e("ContactErrorLog", "Error: ${ex.message}")
                    is NoContactsFoundException ->
                        Log.e("ContactErrorLog", "Error: ${ex.message}")
                }
            }

        }
    }

}