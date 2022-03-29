package com.honeykoders.bankodemia.model.contacts

import java.io.Serializable


data class ContactsMain(
    var success: Boolean? = null,
    var data: Data
) : Serializable