package com.honeykoders.bankodemia.network

import android.content.Context
import com.honeykoders.bankodemia.model.ResponseTransactionCreatedDeposit
import com.honeykoders.bankodemia.network.services.ApiService
import com.honeykoders.bankodemia.ui.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ServiceNetwork(context: Context){
    val retrofit = RetrofitInstance.getRetrofit(context).create(ApiService::class.java)

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

    suspend fun getUserProfile(): Response<UserProfileResponse>{
        return withContext(Dispatchers.IO){
            val response = retrofit.getUserProfile()
            response
        }
    }

    suspend fun addNewContact(newContact: AddNewContactModel):Response<ContactAdded>{
        return withContext(Dispatchers.IO){
            val response = retrofit.addNewContact(newContact)
            response
        }
    }

    suspend fun makeTransactionDeposit (makeTransactionDeposit: MakeTransactionDeposit):Response<ResponseTransactionCreatedDeposit>{
        return withContext(Dispatchers.IO){
            val response = retrofit.makeTransactionDeposit(makeTransactionDeposit)
            response
        }
    }

}