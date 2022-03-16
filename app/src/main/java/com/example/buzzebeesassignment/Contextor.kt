package com.example.buzzebeesassignment

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object Contextor {
    private lateinit var context: Context

    fun init(context: Context){
        Contextor.context = context
    }

    fun getContext() : Context {
        return context
    }
}