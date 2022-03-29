package com.honeykoders.bankodemia.model

import com.google.gson.annotations.SerializedName

/*
{
   "success": true,
   "data": {
       "users": []
   }
}
* */
data class SearchUsersModel (
    @SerializedName("success" ) var success : Boolean? = null,
    @SerializedName("data"    ) var data    : DataSearchUser

)

data class DataSearchUser (
    @SerializedName("users" ) var users : ArrayList<String>
)
