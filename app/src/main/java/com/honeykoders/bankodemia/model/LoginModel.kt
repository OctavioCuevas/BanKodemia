package com.honeykoders.bankodemia.model

/*
Example:
{
  "email": "koder@kodemia.mx",
  "password": "BondJames007"
}
 */
data class LoginModel(
    val email: String,
    val password: String
)
