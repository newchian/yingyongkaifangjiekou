package com.example.ninhaoshijie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_retrofit_test.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RetrofitTestActivity : AppCompatActivity() {
  private lateinit var ipZhuangtai:String
  private lateinit var duankouhaoZhuangtai:String
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_retrofit_test)
    if (savedInstanceState != null) {
      edip.setText(savedInstanceState.getString("ip_zhuangtai",""))
      eddk.setText(savedInstanceState.getString("duankouhao_zhuangtai",""))
      textView6.text = "savedInstanceState: ip_zhuangtai ${savedInstanceState.getString("ip_zhuangtai","")} | duankouhao_zhuangtai ${savedInstanceState.getString("duankouhao_zhuangtai","")} "
    }
    getAppDataBtn.setOnClickListener {
      val ip = edip.text.toString()?:"10.0.2.2"
      val dk = eddk.text.toString()?:"8080"
      ipZhuangtai = ip
      duankouhaoZhuangtai = dk
      val retrofit = Retrofit.Builder()
        .baseUrl("http://$ip:$dk/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
      val appService = retrofit.create(AppService::class.java)
      appService.getAppData().enqueue(object : Callback<List<App>> {
        override fun onResponse(call: Call<List<App>>,response: Response<List<App>>) {
          val list = response.body()
          if (list != null) {
            for (app in list) {
              Log.d("主活动", "id is ${app.id}")
              Log.d("主活动", "name is ${app.name}")
              Log.d("主活动", "version is ${app.version}")
              runOnUiThread {
                textView6.text = textView6.text.toString() + "id : ${app.id}|name : ${app.name}|version : ${app.version}"
              }
            }
          }
        }
        override fun onFailure(call: Call<List<App>>, t: Throwable) {
          t.printStackTrace()
        }
      })
      finish()
    }

  }
  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putString("ip_zhuangtai", ipZhuangtai)
    outState.putString("duankouhao_zhuangtai", duankouhaoZhuangtai)
  }
}







