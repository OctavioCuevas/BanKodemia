package com.honeykoders.bankodemia.ui.model.contacts

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("contacts")
    var contacts: List<Contacts>
)