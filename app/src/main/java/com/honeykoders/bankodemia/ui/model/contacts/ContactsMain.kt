package com.honeykoders.bankodemia.ui.model.contacts

import java.io.Serializable


data class ContactsMain(
    var success: Boolean? = null,
    var data: Data
) : Serializable