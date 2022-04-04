package com.honeykoders.bankodemia.model

import com.google.gson.annotations.SerializedName

/*
{
   "success": true,
   "data": {
       "users": []
   }

   "_id": "623e8e31856dbc3e115f94f4",
                "phone": "+524496352874",
                "lastName": "Sanchez",
                "name": "Corina",
                "email": "cory@mail.com",
}
* */
data class SearchUsersModel (
    @SerializedName("success" ) var success : Boolean? = null,
    @SerializedName("data"    ) var data    : DataSearchUser

)

data class DataSearchUser (
    @SerializedName("users" ) var users : ArrayList<UsersSearchUser>
)

data class UsersSearchUser(
    val _id: String,
    val phone: String,
    val lastName: String,
    val name: String,
    val email: String
)
