package com.example.ninhaoshijie

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import startActivity
import kotlin.jvm.java

class MainActivity : AppCompatActivity(), View.OnClickListener {
  var imgId = 0
  override fun onClick(v: View?) {
    when (v?.id) {
      R.id.annui-> {
        fun quShurukuangWenbenBingJiangQiZhanshiChulai() {
          val inputText = editText.text.toString()
          Toast.makeText(this, inputText, Toast.LENGTH_SHORT).show()
        }

        fun shezhiTupianShituZhiTupianZiyuan() {
          imageView.setImageResource(R.drawable.img_2)
        }

        fun fanzhuanTupian() {
          if(imgId != R.drawable.img_2){
            imageView.setImageResource(R.drawable.img_2)
            imgId = R.drawable.img_2
          }else if(imgId == R.drawable.img_2){
            imageView.setImageResource(R.drawable.img_1)
            imgId = R.drawable.img_1
          }
        }

        fun kaiguanJindutiao() {
          if (progressBar.visibility == View.VISIBLE) {
            progressBar.visibility = View.GONE
          } else {
            progressBar.visibility = View.VISIBLE
          }
        }

        fun zouJindu() {
          progressBar.progress = progressBar.progress + 10
        }

        fun jianzaoJinggaoDuihuakuang() {
          AlertDialog.Builder(this).apply {
            setTitle("这是对话框")
            setMessage("某种非常重要的事情。")
            setCancelable(false)
            setPositiveButton("确定") { _, _ ->
            }
            setNegativeButton("取消") { _, _ ->
            }
            show()
          }
        }
        jianzaoJinggaoDuihuakuang()
      }
      R.id.uiChuangkouxiaobujianAnnui-> {
        fun qidongUIBujuHuodong() {
          val intent = Intent(this, UIBujuHuodong::class.java)
          startActivity(intent)
        }

        fun qidongZidingyiKongjianHuodong() {
          val intent = Intent(this, ZidingyiKongjianHuodong::class.java)
          startActivity(intent)
        }
        qidongUIBujuHuodong();
      }
      R.id.suipianAnnui -> {
        fun qingdongSuipianHuodong() {
          startActivity(Intent(this, SuipianHuodong::class.java))
        }

        startActivity(Intent(this, XinwenHuodong::class.java))
      }
      R.id.guangboAnnui -> {
        fun qidongBroadcastTest() {
          startActivity(Intent(this, BroadcastTest::class.java))
        }

        fun yitu(): Intent {
          val intent = Intent("com.example.ninhaoshijie.MY_BROADCAST")
          intent.setPackage(packageName)
          return intent
        }

        fun fasongMyBroadcast() {
          sendBroadcast(yitu())
        }

        fun fasongYouxuGuangbo() {
          sendOrderedBroadcast(yitu(), null)
        }

        startActivity(Intent(this, LoginActivity::class.java))
      }
      R.id.chijiuhuaAnnui-> {
        fun qidongWenjianChijiuhuaCeshiHuodong() {
          startActivity(Intent(this, WenjianChijiuhuaCeshiHuodong::class.java))
        }

        fun qidongSharedPreferencesTestActivity() {
          startActivity(Intent(this, SharedPreferencesTestActivity::class.java))
        }

        fun qidongDatabaseTestActivity() {
          startActivity(Intent(this, DatabaseTestActivity::class.java))
        }

        fun qidongRuntimePermissionTestActivity() {
          startActivity(Intent(this, RuntimePermissionTestActivity::class.java))
        }

        startActivity(Intent(this, ContactsTestActivity::class.java))
      }
      R.id.meitiAnnui-> {
        fun qidongNotificationTestActivity() {
          startActivity(Intent(this, NotificationTestActivity::class.java))
        }

        fun qidongZhaoxiangji() {
          startActivity(Intent(this, CameraAlbumTestActivity::class.java))
        }

        fun qidongPlayAudioTestActivity() {
          startActivity(Intent(this, PlayAudioTestActivity::class.java))
        }

        startActivity(Intent(this, PlayVideoTestActivity::class.java))
      }
      R.id.serviceAnnui-> {
        fun qidongAndroidThreadTestActivity() {
          startActivity(Intent(this, AndroidThreadTestActivity::class.java))
        }

        startActivity(Intent(this, ServiceTestActivity::class.java))
      }
      R.id.webAnnui-> {
        /*startActivity<WebViewTestActivity>(this){
          putExtra("param1", "data")
          putExtra("param2", 123)
        }*/
//        startActivity<NetworkTestActivity>(this)
        //startActivity(Intent(this, WebViewTestActivity::class.java))
        startActivity<RetrofitTestActivity>(this)
      }
      R.id.peizhiAnnui-> {
        startActivity<WangluoPeizhiActivity>(this)
      }
      R.id.peizhiMD-> {
        startActivity<MaterialTestActivity>(this)
      }
      R.id.vmBtn-> {
        startActivity<JetpackTestActivity>(this)
      }
    }
  }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    annui.setOnClickListener(this)
    uiChuangkouxiaobujianAnnui.setOnClickListener(this)
    suipianAnnui.setOnClickListener(this)
    guangboAnnui.setOnClickListener(this)
    chijiuhuaAnnui.setOnClickListener(this)
    meitiAnnui.setOnClickListener(this)
    serviceAnnui.setOnClickListener(this)
    webAnnui.setOnClickListener(this)
    peizhiAnnui.setOnClickListener(this)
    peizhiMD.setOnClickListener(this)
    vmBtn.setOnClickListener(this)
  }
}
