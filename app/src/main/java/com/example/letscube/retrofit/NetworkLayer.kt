package com.example.letscube.retrofit

import android.content.Context
import android.content.Intent
import android.net.Uri
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkLayer {


    fun launchOauth(context: Context)
    {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.worldcubeassociation.org/oauth/authorize?client_id=" +
                applicationId + "&response_type=code&redirect_uri=" + callbackUri +
        "&scope=email%20dob%20public"))
        context.startActivity(intent)
    }

    fun getRetrofitBuilder() : Retrofit {
        val builder = Retrofit.Builder()
            .baseUrl("https://www.worldcubeassociation.org/")
            .addConverterFactory(GsonConverterFactory.create())

        return builder.build()
    }

    companion object
    {
        val callbackUri: String = "letscube.android://callback"
        val applicationId: String = "pS--DP5yR2UimuSBVLlIzckBn1syxREQtWApAQvFUzE"
        val clientSecret: String = "OdgY0O5RRBRWJh9d-D1TDmtIcX2VWPddPpPuDSPJh1Q"
    }
}