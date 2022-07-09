package com.example.ninhaoshijie

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import androidx.media.app.NotificationCompat
//import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_notification_test.*

class NotificationTestActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_notification_test)
    val manager = getSystemService(/*Context.*/NOTIFICATION_SERVICE) as NotificationManager

    fun qudao() {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel =
          NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
        manager.createNotificationChannel(channel)
      }
    }

    //qudao()
    sendNotice.setOnClickListener {
      val intent = Intent(this, NotificationActivity::class.java)
      val pi = PendingIntent.getActivity(this, 0, intent, 0)

      fun jiaowanzhengdeTongzhi(tz: androidx.core.app.NotificationCompat.Builder) {
        tz.setContentTitle("这是内容标题")
          .setContentText("这是内容文本")
          .setWhen(System.currentTimeMillis())
          .setSmallIcon(R.drawable.ic_launcher)
          .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.ic_launcher))
          .setContentIntent(pi)
      }

      fun builder() = androidx.core.app.NotificationCompat.Builder(this, "normal").apply {
        qudao()
        jiaowanzhengdeTongzhi(this)
      }.build()

      fun builder2() = androidx.core.app.NotificationCompat.Builder(this).apply {
        jiaowanzhengdeTongzhi(this)
      }.build()

      fun builder3(): Notification {
        val n = Notification()
        n.tickerText = "追踪文本"
        n.contentIntent = pi
        n.icon = R.drawable.ic_launcher
        return n
      }

     /* fun builder4(): Notification {
        val n = android.support.v4.app.
      }*/

      val notification = if (Build.VERSION.SDK_INT >= 26) {
        builder()
      }else {
        builder2()
      }
      manager.notify(1, notification as Notification?)
    }

  }
}
