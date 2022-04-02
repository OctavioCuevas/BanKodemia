package com.honeykoders.bankodemia.model

/*
{
    "success": true,
    "data": {
        "transaction": {
            "_id": "62479ce1856dbc3e115fb89e",
            "concept": "Orden de tacos prueba",
            "issuer": {
                "_id": "623e8e31856dbc3e115f94f4",
                "lastName": "Sanchez",
                "name": "Corina",
                "email": "cory@mail.com",
                "__v": 0
            },
            "type": "DEPOSIT",
            "amount": 100,
            "created_at": "2022-04-02T00:46:25.349Z",
            "__v": 0
        },
        "finalBalance": 585
    }
}
*/

data class ResponseTransactionCreatedDeposit(
    val success: Boolean,
    val data: DataDeposit
)

data class DataDeposit(
    val transaction: TransactionDeposit,
    val finalBalance: Int
)

data class TransactionDeposit(
    val _id: String,
    val concept: String,
    val issuer: IssuerDeposit,
    val type: String,
    val amount: Int,
    val created_at: String
)

data class IssuerDeposit(
    val _id: String,
    val lastName:String,
    val name: String,
    val email: String
)