package com.honeykoders.bankodemia.network.models

import android.content.Context
import com.honeykoders.bankodemia.ui.model.contacts.ContactsMain
import com.honeykoders.bankodemia.network.RetrofitInstance
import com.honeykoders.bankodemia.network.services.ContactService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class GetContacts(context: Context) {

    private val retrofit = RetrofitInstance.getRetrofit(context).create(ContactService::class.java)

    suspend fun getContactsService(): Response<ContactsMain> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.getUserContacts()
            response
        }
    }
}