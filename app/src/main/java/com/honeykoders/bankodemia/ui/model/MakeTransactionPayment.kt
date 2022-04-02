package com.honeykoders.bankodemia.ui.model

data class MakeTransactionPayment(
    val amount: Int,
    val type: String,
    val destinationUser: String,
    val concept: String
)