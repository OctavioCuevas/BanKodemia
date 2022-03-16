package com.honeykoders.bankodemia.model
/*
{
  "amount": 500,
  "type": "DEPOSIT",
  "destinationUser": "6204409a5696f4000da56098",
  "concept": "Orden de tacos",
}
* */
data class MakeTransaction(
    val amount: Int,
    val type: String,
    val concept: String
)
