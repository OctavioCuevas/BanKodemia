package com.honeykoders.bankodemia.network

import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.model.*
import retrofit2.Response
import retrofit2.http.*

val utils: Utils = Utils()

interface ApiService {
    @Headers("Content-Type: application/json ","Accept: application/json")
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

    /*
    * @GET("data/2.5/onecall")
    suspend fun getWeatherById(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String?,
        @Query("lang") lang: String?,
        @Query("appid") appid: String): OneCallEntity
    *
    *
    * */

}