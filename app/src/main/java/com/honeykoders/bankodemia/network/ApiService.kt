package com.honeykoders.bankodemia.network

import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.model.*
import retrofit2.Response
import retrofit2.http.*


interface ApiService {

    //SingUp
    @POST("/users")
    suspend fun singUp(@Body singUpModel: SingUpModel): Response<ResponseSingUp>
    //Transactions
    //@Headers("Authorization: Bearer  ")
    @POST("/transactions")
    suspend fun makeTransactionPayment(@Body makeTransaction: MakeTransactionPayment): Response<ResponseTransactionCreated>
    //Login
    @POST("/auth/login")
    suspend fun loginUser(@Body login: LoginModel): Response<ResponseUserLoggedIn>
    //Search Users
    @GET("/users/search")
    suspend fun searchUsers(@Query("query") query: String): Response<SearchUsersModel>

}