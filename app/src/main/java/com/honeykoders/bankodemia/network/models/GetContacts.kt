package com.honeykoders.bankodemia.network.models

import android.content.Context
import com.honeykoders.bankodemia.model.contacts.ContactsMain
import com.honeykoders.bankodemia.network.RetrofitInstance
import com.honeykoders.bankodemia.network.services.ContactService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class GetContacts() {

    private val retrofit = RetrofitInstance.getRetrofit().create(ContactService::class.java)

    suspend fun getContactsService(): Response<ContactsMain> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.getUserContacts()
            response
        }
    }
}