package com.honeykoders.bankodemia.model.contacts

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("contacts")
    var contacts: List<Contacts>
)