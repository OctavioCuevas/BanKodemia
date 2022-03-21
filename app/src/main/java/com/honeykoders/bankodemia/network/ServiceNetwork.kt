package com.honeykoders.bankodemia.network

import com.honeykoders.bankodemia.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Response
import java.io.File

class ServiceNetwork {
    val retrofit = RetrofitInstance.getRetrofit().create(ApiService::class.java)

   //suspend fun enviarFoto(foto: File): Response<Respuesta> {
        //Conversion de glide ---> requestbody
     //   val profile = RequestBody.create(MediaType.parse("imagen/*"),foto)
        //Corrutina
       // return withContext(Dispatchers.IO){
           // val response = retrofit.enviarFoto(profile)
            //response
        //}
    //}

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
}