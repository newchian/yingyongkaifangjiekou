package com.example.ninhaoshijie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.qiangzhi_lixian_guangbo_fasong_huodong_buju.*

class QiangzhiLixianGuangboFasongHuodong : ChouxiangHuodong() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.qiangzhi_lixian_guangbo_fasong_huodong_buju)
    forceOffline.setOnClickListener {
      val intent = Intent("com.example.ninhaoshijie.FORCE_OFFLINE")
      sendBroadcast(intent)
    }

  }
}
