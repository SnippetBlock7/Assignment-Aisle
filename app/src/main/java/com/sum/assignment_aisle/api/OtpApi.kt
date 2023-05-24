package com.sum.assignment_aisle.api

import com.sum.assignment_aisle.model.OtpModel
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface OtpApi {

    @POST("users/verify_otp")
    suspend fun getResponse(@Query("number") number: String, @Query("otp") otp: String): OtpModel

}