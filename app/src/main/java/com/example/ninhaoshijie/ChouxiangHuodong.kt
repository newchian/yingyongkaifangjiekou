package com.example.ninhaoshijie

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

open class ChouxiangHuodong : AppCompatActivity(){
  lateinit var receiver: ForceOfflineReceiver
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    HuodongShoujiqi.addActivity(this)
  }
  override fun onResume() {
    super.onResume()
    val intentFilter = IntentFilter()
    intentFilter.addAction("com.example.ninhaoshijie.FORCE_OFFLINE")
    receiver = ForceOfflineReceiver()
    registerReceiver(receiver, intentFilter)
  }
  override fun onPause() {
    super.onPause()
    unregisterReceiver(receiver)
  }
  override fun onDestroy() {
    super.onDestroy()
    HuodongShoujiqi.removeActivity(this)
  }
  inner class ForceOfflineReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
      AlertDialog.Builder(context).apply {
        setTitle("警告Warning")
        setMessage("将要强制你离线。请再一次尝试登录吧。")
        setCancelable(false)
        setPositiveButton("好") { _, _ ->
          HuodongShoujiqi.finishAll() // 销毁所有Activity
          val i = Intent(context, LoginActivity::class.java)
          context.startActivity(i) // 重新启动LoginActivity
        }
        show()
      }
    }
  }
}