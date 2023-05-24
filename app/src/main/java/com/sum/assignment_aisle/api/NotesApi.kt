package com.sum.assignment_aisle.api

import com.sum.assignment_aisle.model.notesmodel.Notes
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface NotesApi {

    @GET("users/test_profile_list")
    suspend fun getProfileList(@Header("Authorization") token: String): Notes
}