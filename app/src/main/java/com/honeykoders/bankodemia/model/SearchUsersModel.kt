package com.honeykoders.bankodemia.model
/*
{
   "success": true,
   "data": {
       "users": []
   }
}
* */
data class SearchUsersModel (
    val success: String,
    val data: Users
)

data class Users (
    val users: List<String>
    )
