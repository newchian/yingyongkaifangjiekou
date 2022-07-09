package com.example.ninhaoshijie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_network_test.*
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.xml.sax.InputSource
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringReader
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.parsers.SAXParserFactory
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetworkTestActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_network_test)
    val ip = intent.getStringExtra("ip")?:"10.0.2.2"
    val duankou = intent.getStringExtra("duankou")?:"80"
    responseText.text = " ip地址：$ip  端口号：$duankou ip${intent.getStringExtra("ip")}  duankou${intent.getStringExtra("duankou")} za${intent.getStringExtra("za")}"
    sendRequestBtn.setOnClickListener {

      fun showResponse(response: String) {
        runOnUiThread {
          // 在这里进行UI操作，将结果显示到界面上
          responseText.text = response
        }
      }

      fun parseXMLWithPull(xmlData: String) {
        try {
          val factory = XmlPullParserFactory.newInstance()
          val xmlPullParser = factory.newPullParser()
          xmlPullParser.setInput(StringReader(xmlData))
          var eventType = xmlPullParser.eventType
          var id = ""
          var name = ""
          var version = ""
          while (eventType != XmlPullParser.END_DOCUMENT) {
            val nodeName = xmlPullParser.name
            when (eventType) {
// 开始解析某个节点
              XmlPullParser.START_TAG -> {
                when (nodeName) {
                  "id" -> id = xmlPullParser.nextText()
                  "name" -> name = xmlPullParser.nextText()
                  "version" -> version = xmlPullParser.nextText()
                }
              }
// 完成解析某个节点
              XmlPullParser.END_TAG -> {
                if ("app" == nodeName) {
                  Log.d("主活动", "id is $id")
                  Log.d("主活动", "name is $name")
                  Log.d("主活动", "version is $version")

                  runOnUiThread {
                    // 在这里进行UI操作，将结果显示到界面上
                    val t = responseText.text
                    responseText.text = t.toString() + " id：$id  name：$name  version：$version"
                  }
                }
              }
            }
            eventType = xmlPullParser.next()
          }
        } catch (e: Exception) {
          e.printStackTrace()
        }
      }

      fun sendRequestWithHttpURLConnection() {
// 开启线程发起网络请求
        thread {
          var connection: HttpURLConnection? = null
          try {
            val response = StringBuilder()
            val url = URL("https://www.baidu.com")
            connection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = 8000
            connection.readTimeout = 8000
            val input = connection.inputStream
// 下面对获取到的输入流进行读取
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
              reader.forEachLine {
                response.append(it)
              }
            }
            showResponse(response.toString())
          }catch (e: Exception) {
            e.printStackTrace()
          }finally {
            connection?.disconnect()
          }
        }
      }

      fun parseXMLWithSAX(xmlData: String) {
        try {
          val factory = SAXParserFactory.newInstance()
          val xmlReader = factory.newSAXParser().xmlReader
          val handler = ContentHandler()
// 将ContentHandler的实例设置到XMLReader中
          xmlReader.contentHandler = handler
// 开始执行解析
          xmlReader.parse(InputSource(StringReader(xmlData)))
        } catch (e: Exception) {
          e.printStackTrace()
        }
      }

      fun parseJSONWithJSONObject(jsonData: String) {
        try {
          val jsonArray = JSONArray(jsonData)
          for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val id = jsonObject.getString("id")
            val name = jsonObject.getString("name")
            val version = jsonObject.getString("version")
            Log.d("主活动", "id is $id")
            Log.d("主活动", "name is $name")
            Log.d("主活动", "version is $version")
          }
        }catch (e: Exception) {
          e.printStackTrace()
        }
      }

      fun parseJSONWithGSON(jsonData: String) {
        val gson = Gson()
        val typeOf = object : TypeToken<List<App>>() {}.type
        val appList = gson.fromJson<List<App>>(jsonData, typeOf)
        for (app in appList) {
          Log.d("主活动", "id is ${app.id}")
          Log.d("主活动", "name is ${app.name}")
          Log.d("主活动", "version is ${app.version}")
        }
      }

      fun sendRequestWithOkHttp() {
        thread {
          try {
            val client = OkHttpClient()
            val request = Request.Builder()
              //.url("http://$ip:$duankou/get_data.xml")
              .url("http://$ip:$duankou/get_data.json")
              .build()
            val response = client.newCall(request).execute()
            val responseData = response.body()?.string()
            if (responseData != null) {
//              parseXMLWithPull(responseData)
//              parseXMLWithSAX(responseData)
//              parseJSONWithJSONObject(responseData)
              parseJSONWithGSON(responseData)
            }
          }catch (e: Exception) {
            e.printStackTrace()
          }
        }
      }

      sendRequestWithOkHttp()
//      sendRequestWithHttpURLConnection()
    }

  }
}

class App(val id: String, val name: String, val version: String)

interface HttpCallbackListener {
  fun onFinish(response: String)
  fun onError(e: Exception)
}

object HttpUtil {
  fun sendHttpRequest(address: String, listener: HttpCallbackListener) {
    thread {
      var connection: HttpURLConnection? = null
      try {
        val response = StringBuilder()
        val url = URL(address)
        connection = url.openConnection() as HttpURLConnection
        connection.connectTimeout = 8000
        connection.readTimeout = 8000
        val input = connection.inputStream
        val reader = BufferedReader(InputStreamReader(input))
        reader.use {
          reader.forEachLine {
            response.append(it)
          }
        }
// 回调onFinish()方法
        listener.onFinish(response.toString())
      } catch (e: Exception) {
        e.printStackTrace()
// 回调onError()方法
        listener.onError(e)
      } finally {
        connection?.disconnect()
      }
    }
  }
  fun sendOkHttpRequest(address: String, callback: Callback) {
    val client = OkHttpClient()
    val request = Request.Builder()
      .url(address)
      .build()
    client.newCall(request).enqueue(callback)
  }

}

interface AppService {
  @GET("get_data.json")
  fun getAppData(): Call<List<App>>
}

object ServiceCreator {
  private const val BASE_URL = "http://10.0.2.2/"
  private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
  fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
  inline fun <reified T> create(): T = create(T::class.java)
}

suspend fun request(address: String): String {
  return suspendCoroutine { continuation ->
    HttpUtil.sendHttpRequest(address, object : HttpCallbackListener {
      override fun onFinish(response: String) {
        continuation.resume(response)
      }
      override fun onError(e: Exception) {
        continuation.resumeWithException(e)
      }
    })
  }
}

suspend fun <T> Call<T>.await(): T {
  return suspendCoroutine { continuation ->
    enqueue(object : retrofit2.Callback<T> {
      override fun onResponse(call: Call<T>, response: Response<T>) {
        val body = response.body()
        if (body != null) continuation.resume(body)
        else continuation.resumeWithException(
          RuntimeException("response body is null"))
      }
      override fun onFailure(call: Call<T>, t: Throwable) {
        continuation.resumeWithException(t)
      }
    })
  }
}





















