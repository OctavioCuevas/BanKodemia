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
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MjJhOTk3NzhjZTZjNDc4ZDBlMWJmZDEiLCJpYXQiOjE2NDc0MDIwMTcsImV4cCI6MTY0NzQwNTYxN30.InI-CY5EsrpuryqLhoH0eMpaTYOb9ImRtY4E0OmaHTc")
    @POST("/transactions")
    suspend fun makeTransactionPayment(@Body makeTransaction: MakeTransactionPayment): Response<ResponseTransactionCreated>
}