package com.honeykoders.bankodemia.use_cases

import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.exceptions.EmptyTokenException
import com.honeykoders.bankodemia.viewmodel.SendMoneyVM

class GetUserContacts {

    val utils = Utils()

    var token: String = ""
        set(value) {
            field = value.ifEmpty { throw EmptyTokenException() }
        }

    lateinit var viewModel: SendMoneyVM

    fun getContacts() {
        this.getContactsData()
    }

    private fun getContactsData() {
        this.viewModel.getUserContacts()
    }
}