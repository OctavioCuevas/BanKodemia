package com.honeykoders.bankodemia.ui.model.contacts

import com.google.gson.annotations.SerializedName

data class Contacts(
    @SerializedName("_id") var Id: String? = null,
    @SerializedName("user") var user: User? = User(),
    @SerializedName("owner") var owner: Owner? = Owner(),
    @SerializedName("shortName") var shortName: String? = null,
    @SerializedName("__v") var _v: Int? = null
)