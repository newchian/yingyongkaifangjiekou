package com.example.ninhaoshijie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import kotlinx.android.synthetic.main.activity_android_thread_test.*
import kotlin.concurrent.thread

class AndroidThreadTestActivity : AppCompatActivity() {
  val updateText = 1
  val handler = object : Handler(Looper.getMainLooper()) {
    override fun handleMessage(msg: Message) {
// 在这里可以进行UI操作
      when (msg.what) {
        updateText -> textView.text = "Nice to meet you"
      }
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_android_thread_test)
    changeTextBtn.setOnClickListener {
      thread {
        val msg = Message()
        msg.what = updateText
        handler.sendMessage(msg) // 将Message对象发送出去
      }
    }

  }
}
