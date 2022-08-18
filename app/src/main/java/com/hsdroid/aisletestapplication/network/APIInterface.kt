package com.hsdroid.aisletestapplication.network

import NotesResponse
import com.hsdroid.aisletestapplication.data.ResponseLogin
import com.hsdroid.aisletestapplication.data.ResponseOtp
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @FormUrlEncoded
    @POST("users/phone_number_login")
    fun loginPhone(
        @Field("number") number: String
    ): Call<ResponseLogin>


    @FormUrlEncoded
    @POST("users/verify_otp")
    fun verifyOtp(
        @Field("number") number: String,
        @Field("otp") otp: String
    ): Call<ResponseOtp>


    @GET("users/test_profile_list")
    fun getNotes(
        @Header("Authorization") token: String
    ): Call<NotesResponse>


}