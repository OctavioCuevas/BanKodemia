package com.honeykoders.bankodemia.model



/*
* {
    "success": true,
    "data": {
        "user": {
            "_id": "623e8e31856dbc3e115f94f4",
            "lastName": "Sanchez",
            "name": "Corina",
            "email": "cory@mail.com",
            "__v": 0
        },
        "transactions": [
        * {
             "_id": "624397d2856dbc3e115fa561",
            "concept": "Deposito inicial",
            "issuer": {
                "_id": "623e8e31856dbc3e115f94f4",
                "lastName": "Sanchez",
                "name": "Corina",
                "email": "cory@mail.com",
                "__v": 0
            },
            "type": "DEPOSIT",
            "amount": 500,
            "created_at": "2022-03-29T23:35:46.146Z",
            "__v": 0],
        "balance": 0
    },
    {
                "isIncome": false,
                "_id": "62439a3b856dbc3e115fa575",
                "concept": "Pago restaurante",
                "destinationUser": {
                    "_id": "623fd78c856dbc3e115f95e6",
                    "lastName": "Garcia",
                    "name": "Takeshi",
                    "email": "takeshi@mail.com",
                    "__v": 0
                },
                "issuer": {
                    "_id": "623e8e31856dbc3e115f94f4",
                    "lastName": "Sanchez",
                    "name": "Corina",
                    "email": "cory@mail.com",
                    "__v": 0
                },
                "type": "PAYMENT",
                "amount": 15,
                "created_at": "2022-03-29T23:46:03.545Z",
                "__v": 0
            }
}
* */
data class UserProfileResponse (
    val success: Boolean,
    val data: DataInfo
    )

data class Transactions (
    val isIncome: Boolean,
    val concept: String,
    val destinationUser: UserProfileDestination,
    val issuer: IssuerProfile,
    val type: String,
    val amount: Int,
    val created_at: String
        )

data class IssuerProfile (
    val _id: String,
    val lastName: String,
    val name: String,
    val email: String
    )

data class UserProfileDestination (
    val _id: String,
    val lastName: String,
    val name: String,
    val email: String
)

data class UserProfile (
    val _id: String,
    val lastName: String,
    val name: String,
    val email: String
)

data class DataInfo (
    val user: UserProfile,
    val transactions: List<Transactions>,
    val balance: Int
    )


