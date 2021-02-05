package com.example.letscube.retrofit

import com.example.letscube.model.AccessToken
import com.example.letscube.model.CurrentUser
import com.example.letscube.model.User
import retrofit2.Call
import retrofit2.http.*

interface APIClass
{
    @Headers("Accept: application/json")
    @POST("oauth/token")
    @FormUrlEncoded
    fun getAccessToken(
        @Field("grant_type") type: String,
        @Field("client_id") username: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String,
        @Field("redirect_uri") uri: String
    ) : Call<AccessToken>


    @GET("api/v0/me")
    fun getUser(@Header("Authorization") token: String) : Call<CurrentUser>
}