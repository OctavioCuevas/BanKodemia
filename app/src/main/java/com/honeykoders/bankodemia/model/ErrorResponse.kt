package com.honeykoders.bankodemia.model
/*
{
    "statusCode": 401,
    "message": "Invalid credentials",
    "error": "Unauthorized"
}
*/
data class ErrorResponse (
    val statusCode: Int,
    val message: String,
    val error: String
)
