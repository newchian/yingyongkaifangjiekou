package com.example.ninhaoshijie

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import kotlinx.android.synthetic.main.activity_wangluo_peizhi.*
import kotlinx.android.synthetic.main.activity_wangluo_peizhi.view.*
import startActivity

class WangluoPeizhiActivity : AppCompatActivity() {
  private lateinit var ipZhuangtai:String
  private lateinit var duankouhaoZhuangtai:String
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_wangluo_peizhi)
    val prefs = getPreferences(Context.MODE_PRIVATE)

    if (savedInstanceState != null) {
      editIP.setText(savedInstanceState.getString("ip_zhuangtai",""))
      editDuankou.setText(savedInstanceState.getString("duankouhao_zhuangtai",""))
      textView5.text = "savedInstanceState: ip_zhuangtai ${savedInstanceState.getString("ip_zhuangtai","")} | duankouhao_zhuangtai ${savedInstanceState.getString("duankouhao_zhuangtai","")} "
    }
    editIP.setText(prefs.getString("ip_zhuangtai", ""))
    editDuankou.setText(prefs.getString("duankouhao_zhuangtai", ""))
    textView5.text = "prefs: ip_zhuangtai ${prefs.getString("ip_zhuangtai", "")} | duankouhao_zhuangtai ${prefs.getString("duankouhao_zhuangtai", "")} "
    peizhihao.setOnClickListener {
      val textIP = editIP.text
      val textDK = editDuankou.text
      this.ipZhuangtai = textIP.toString()
      this.duankouhaoZhuangtai = textDK.toString()
      val editor = prefs.edit()
      editor.putString("ip_zhuangtai", textIP.toString())
      editor.putString("duankouhao_zhuangtai", textDK.toString())
      editor.apply()
      textView5.text = "ip $textIP duankou $textDK"
      startActivity<NetworkTestActivity>(this){
        textView5.text = textView5.text.toString() + "ip $textIP duankou $textDK"
        putExtra("ip", textIP.toString())
        putExtra("duankou", textDK.toString())
        putExtra("za", "杂杂")
        finish()
      }
    }
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putString("ip_zhuangtai", ipZhuangtai)
    outState.putString("duankouhao_zhuangtai", duankouhaoZhuangtai)
  }

  /*override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
    super.onSaveInstanceState(outState, outPersistentState)
    outPersistentState.putString("ip_zhuangtai", ipZhuangtai)
    outPersistentState.putString("duankouhao_zhuangtai", duankouhaoZhuangtai)
  }*/
}
