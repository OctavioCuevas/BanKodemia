package com.honeykoders.bankodemia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.honeykoders.bankodemia.exceptions.NoContactsFoundException
import com.honeykoders.bankodemia.model.contacts.ContactsMain
import com.honeykoders.bankodemia.network.models.GetContacts
import kotlinx.coroutines.launch

class SendMoneyVM : ViewModel() {
    private var TAG = "SendMoneyError"
    lateinit var serviceGetContacts: GetContacts

    //LiveData
    val contactsResponse = MutableLiveData<ContactsMain>()

    fun onCreateVM() {
        serviceGetContacts = GetContacts()
    }

    fun getUserContacts() {
        this.viewModelScope.launch {
            val response = serviceGetContacts.getContactsService()
            if (response.isSuccessful) {
                contactsResponse.postValue(response.body())
            } else {
                throw NoContactsFoundException()
            }
        }
    }

}