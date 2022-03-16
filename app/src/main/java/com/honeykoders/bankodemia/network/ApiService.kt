package com.honeykoders.bankodemia.network

import com.honeykoders.bankodemia.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json ","Accept: application/json")
    //SingUp
    @POST("/users")
    suspend fun singUp(@Body singUpModel: SingUpModel): Response<ResponseSingUp>
    //Transactions
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MjJhOTk3NzhjZTZjNDc4ZDBlMWJmZDEiLCJpYXQiOjE2NDczOTYwODEsImV4cCI6MTY0NzM5OTY4MX0._0g3DahAGy3T3-HQI3wcLUzTGUN4zMuCZn90eS4maPY")
    @POST("/transactions")
    suspend fun makeTransactionPayment(@Body makeTransaction: MakeTransactionPayment): Response<ResponseTransactionCreated>
}