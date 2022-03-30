package com.honeykoders.bankodemia.network.services

import com.honeykoders.bankodemia.model.contacts.ContactsMain
import com.honeykoders.bankodemia.model.contacts.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ContactService {
    @GET("contacts")
    suspend fun getUserContacts(): Response<ContactsMain>
}