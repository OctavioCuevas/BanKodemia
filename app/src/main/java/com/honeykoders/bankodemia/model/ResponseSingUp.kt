package com.honeykoders.bankodemia.model
/*Example
{
  "success": true,
  "data": {
    "user": {
      "_id": "culpa tempor magna eiusmod aliquip",
      "email": "koder@kodemia.mx",
      "name": "John",
      "lastName": "Doe",
      "occupation": "Software Magician",
      "birthDate": "1954-06-01T20:11:33.416Z",
      "password": "BondJames007",
      "phone": "+525577111588",
      "isPhoneVerified": false,
      "identityImage": "iVBORw0KGgoAAAANSUhEUg...",
      "identityImageType": "INE"
    }
  }
}

 */
data class ResponseSingUp (
    val success: Boolean,
    val data: User
)

data class User(
    val _id: String,
    val email: String,
    val name: String,
    val lastName: String,
    val occupation: String,
    val birthDate: String,
    val password: String,
    val phone: String,
    val isPhoneVerified: Boolean,
    val identityImage: String,
    val identityImageType: String,
)