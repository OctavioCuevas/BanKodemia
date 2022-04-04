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
    @POST("/transactions")
    suspend fun makeTransactionPayment(@Body makeTransaction: MakeTransactionPayment): Response<ResponseTransactionCreated>
    //Login
    @POST("/auth/login")
    suspend fun loginUser(@Body login: LoginModel): Response<ResponseUserLoggedIn>
    //Search Users
    @GET("/users/search")
    suspend fun searchUsers(@Query("query") query: String): Response<SearchUsersModel>
    //Get User Profile
    @GET("/users/me/profile")
    suspend fun getUserProfile(): Response<UserProfileResponse>
    //GetContacts
    @POST("/contacts")
    suspend fun addNewContact(@Body newContact: AddNewContactModel): Response<ContactAdded>
    //Deposit
    @POST("/transactions")
    suspend fun makeTransactionDeposit(@Body makeTransaction: MakeTransactionDeposit): Response<ResponseTransactionCreatedDeposit>

}