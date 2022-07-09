package com.example.ninhaoshijie

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log


class MyIntentService : IntentService("MyIntentService") {

  override fun onHandleIntent(intent: Intent?) {
    // 打印当前线程的id
    Log.d("我的意图服务", "Thread id is ${Thread.currentThread().name}")

  }
  override fun onDestroy() {
    super.onDestroy()
    Log.d("我的意图服务", "onDestroy executed")
  }

}
