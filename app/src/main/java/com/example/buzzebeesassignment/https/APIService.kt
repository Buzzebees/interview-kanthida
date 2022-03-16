package com.example.buzzebeesassignment.https

import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIService {

    companion object {
//        var BASE_URL = "https://firebasestorage.googleapis.com"

//        fun create() : APIService {
//            val retrofit = Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(BASE_URL)
//                .build()
//            return retrofit.create(APIService::class.java)
//        }
    }

    @GET("/v0/b/android-interview-test.appspot.com/o/campaigns.json?alt=media&token=2c4ae9ee-79f1-429e-8e68-47a176ec9348")
    fun getAllCampaign(): retrofit2.Call<ResponseBody>
}