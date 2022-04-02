package com.honeykoders.bankodemia.ui.model
/*
{
  "statusCode": 400,
  "message": [
    "attribute must be of certain type",
    "attribute must be one of the following values: OPTION_ONE, OPTION_TWO, OPTION_THREE"
  ],
  "error": "Bad Request"
}
*/
data class SingUpErrorResponse (
    val statusCode: Int,
    val message: List<String>,
    val error: String
)
