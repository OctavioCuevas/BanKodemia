package com.honeykoders.bankodemia.model.contacts

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("_id")
    var Id: String? = null,
    @SerializedName("lastName")
    var lastName: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("__v")
    var _v: Int? = null

)