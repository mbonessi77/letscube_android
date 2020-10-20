package com.example.letscube.retrofit

import com.example.letscube.model.AccessToken
import com.example.letscube.model.CurrentUser
import com.example.letscube.model.User
import retrofit2.Call
import retrofit2.http.*

interface APIClass
{
    @POST("oauth/token")
    @FormUrlEncoded
    fun getAccessToken(
        @Field("username") username: String,
        @Field("password") clientSecret: String,
        @Field("grant_type") type: String
    ) : Call<AccessToken>


    @GET("api/v0/me")
    fun getUser(@Header("Authorization") token: String) : Call<CurrentUser>
}