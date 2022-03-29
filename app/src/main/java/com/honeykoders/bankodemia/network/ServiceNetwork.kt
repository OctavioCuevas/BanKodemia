package com.honeykoders.bankodemia.network

import android.content.Context
import android.util.Log
import com.honeykoders.bankodemia.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ServiceNetwork(context: Context){
    val retrofit = RetrofitInstance.getRetrofit().create(ApiService::class.java)

    suspend fun singUp (singUp:SingUpModel):Response<ResponseSingUp>{
        return withContext(Dispatchers.IO){
            val response = retrofit.singUp(singUp)
            response
        }
    }

    suspend fun makeTransactionPayment (makeTransactionPayment: MakeTransactionPayment):Response<ResponseTransactionCreated>{
        return withContext(Dispatchers.IO){
            val response = retrofit.makeTransactionPayment(makeTransactionPayment)
            response
        }
    }

    suspend fun loginUser(login: LoginModel):Response<ResponseUserLoggedIn>{
        return withContext(Dispatchers.IO){
            val response = retrofit.loginUser(login)
            response
        }
    }

    suspend fun searchUser(query:String):Response<SearchUsersModel>{
        return withContext(Dispatchers.IO){
            val response = retrofit.searchUsers(query)
            response
        }
    }


}