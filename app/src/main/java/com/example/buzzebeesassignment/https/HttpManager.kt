package com.example.buzzebeesassignment.https

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpManager {
    private var BASE_URL = "https://firebasestorage.googleapis.com"
    var service: APIService

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        service = retrofit.create(APIService::class.java)
    }
}