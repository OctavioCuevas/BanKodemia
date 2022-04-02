package com.honeykoders.bankodemia.ui.model

/*Example
{
    "email": "koder@kodemia.mx",
    "name": "John",
    "lastName": "Doe",
    "birthDate": "2013-04-14T00:40:37.437Z",
    "password": "BondJames007",
    "phone": "+525577111588",
    "identityImage": "iVBORw0KGgoAAAANSUhEUg...",
    "identityImageType": "INE",
    "occupation": "Software Magician",
    "_id": "nulla dolore"
}
 */

data class SingUpModel(
    val email: String,
    val name: String,
    val lastName: String,
    val birthDate: String,
    val password: String,
    val phone: String,
    val identityImage: String,
    val identityImageType: String,
    val occupation: String
)
