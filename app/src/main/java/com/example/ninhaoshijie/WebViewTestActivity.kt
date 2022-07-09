package com.example.ninhaoshijie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web_view_test.*

class WebViewTestActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_web_view_test)
    webView.settings.javaScriptEnabled=true
    webView.webViewClient = WebViewClient()
    webView.loadUrl("https://www.baidu.com")
    Log.d("web视图","参数1是：${intent.getStringExtra("param1")}")
    Log.d("web视图","参数2是：${intent.getIntExtra("param2",0)}")
  }
}
