package com.honeykoders.bankodemia.model
/*
{
  "amount": 500,
  "type": "DEPOSIT",
  "concept": "Orden de tacos",
}
* */
data class MakeTransactionDeposit(
    val amount: Int,
    val type: String,
    val concept: String
)
