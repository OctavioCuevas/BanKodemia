package com.honeykoders.bankodemia.ui.model
/*
Example Value
Schema
{
  "success": true,
  "data": {
    "transaction": {
      "amount": 500,
      "type": "DEPOSIT",
      "concept": "Orden de tacos",
      "created_at": "2022-03-14T00:20:43.467Z",
      "issuer": {
        "email": "koder@kodemia.mx",
        "name": "John",
        "lastName": "Doe",
        "occupation": "Software Magician",
        "birthDate": "1995-11-04",
        "password": "BondJames007",
        "phone": "+525577111588",
        "isPhoneVerified": true,
        "identityImage": "R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7",
        "identityImageType": "INE"
      },
      "destinationUser": {
        "email": "koder@kodemia.mx",
        "name": "John",
        "lastName": "Doe",
        "occupation": "Software Magician",
        "birthDate": "1995-11-04",
        "password": "BondJames007",
        "phone": "+525577111588",
        "isPhoneVerified": true,
        "identityImage": "R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7",
        "identityImageType": "INE"
      },
      "isIncome": true
    },
    "finalBalance": 500
  }
}

 */
data class ResponseTransactionCreated(
 val success:Boolean,
 val data: Data,
 val finalBalance: Int
)

data class Data(
    val transaction: Transaction
)

data class Transaction(
    val amount: Int,
    val type: String,
    val concept: String,
    val created_at: String,
    val issuer: Issuer,
    val destinationUser: DestinationUser,
    val isIncome: Boolean
)

data class Issuer(
    val email:String,
    val name: String,
    val lastName: String,
    val occupation: String,
    val birthDate: String,
    val password: String,
    val phone: String,
    val isPhoneVerified: Boolean,
    val identityImage: String,
    val identityImageType: String
)

data class DestinationUser(
    val email:String,
    val name: String,
    val lastName: String,
    val occupation: String,
    val birthDate: String,
    val password: String,
    val phone: String,
    val isPhoneVerified: Boolean,
    val identityImage: String,
    val identityImageType: String
)