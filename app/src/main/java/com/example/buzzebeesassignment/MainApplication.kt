package com.example.buzzebeesassignment

import android.app.Application

//for init contextor
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Contextor.init(applicationContext)
    }
}