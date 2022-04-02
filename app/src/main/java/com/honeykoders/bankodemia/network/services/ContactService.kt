package com.honeykoders.bankodemia.network.services

import com.honeykoders.bankodemia.ui.model.contacts.ContactsMain
import retrofit2.Response
import retrofit2.http.GET

interface ContactService {
    @GET("contacts")
    suspend fun getUserContacts(): Response<ContactsMain>
}