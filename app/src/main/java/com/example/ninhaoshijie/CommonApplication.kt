package com.example.ninhaoshijie

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class CommonApplication : Application() {
  companion object {
    const val TOKEN = "KSHR8A14mdGfO2gJ"
      @SuppressLint("StaticFieldLeak")
    lateinit var context: Context
  }
  override fun onCreate() {
    super.onCreate()
    context = applicationContext
  }
}
