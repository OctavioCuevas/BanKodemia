package com.honeykoders.bankodemia.network.services

import com.honeykoders.bankodemia.model.contacts.ContactsMain
import com.honeykoders.bankodemia.model.contacts.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ContactService {

    @Headers(
        "Accept: application/json",
        "Autorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MjE4NmRmM2M0NjBiZDNkODZhNjc1MjEiLCJpYXQiOjE2NDg0MjE2NjQsImV4cCI6MTY0ODQyNTI2NH0.2eRC-GmU2vYlpmeScpCefMiQLbXWBSUjtHBYYjFzQEU"
    )
    @GET("contacts")
    suspend fun getUserContacts(): Response<ContactsMain>
}