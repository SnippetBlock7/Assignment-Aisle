package com.sum.assignment_aisle.api

import com.sum.assignment_aisle.model.PhoneNumberModel
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface PhoneNumberAPI {

    @POST("users/phone_number_login")
    suspend fun getStatus(@Query("number") number: String): PhoneNumberModel

}