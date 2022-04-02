package com.honeykoders.bankodemia.ui.model
/*
{
    "success": true,
    "data": {
        "contact": {
            "user": "623e8e31856dbc3e115f94f4",
            "owner": "623e8e31856dbc3e115f94f4",
            "shortName": "Compadre Luis Antonio",
            "_id": "6243ae2d856dbc3e115fa629"
        }
    }
}

*/
data class ContactAdded(
    val success: String,
    val data: DataContact
)

data class DataContact(
    val contact: Contact
)

data class Contact(
    val user: String,
    val owner: String,
    val shortName: String,
    val _id: String
)
