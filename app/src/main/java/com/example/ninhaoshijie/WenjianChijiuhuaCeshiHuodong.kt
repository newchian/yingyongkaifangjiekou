package com.example.ninhaoshijie

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.wenjian_chijiuhua_ceshi_buju.*
import java.io.*

class WenjianChijiuhuaCeshiHuodong : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.wenjian_chijiuhua_ceshi_buju)

    fun load(): String {
      val content = StringBuilder()
      try {
        val input = openFileInput("data")
        val reader = BufferedReader(InputStreamReader(input))
        reader.use {
          reader.forEachLine {
            content.append(it)
          }
        }
      } catch (e: IOException) {
        e.printStackTrace()
      }
      return content.toString()
    }

    val inputText = load()
    if (inputText.isNotEmpty()) {
      editText.setText(inputText)
      editText.setSelection(inputText.length)
      Toast.makeText(this, "还原成功了", Toast.LENGTH_SHORT).show()
    }
  }
  override fun onDestroy() {
    super.onDestroy()
    val inputText = editText.text.toString()

    fun save(inputText: String) {
      try {
        val output = openFileOutput("data", Context.MODE_PRIVATE)
        val writer = BufferedWriter(OutputStreamWriter(output))
        writer.use {
          it.write(inputText)
        }
      } catch (e: IOException) {
        e.printStackTrace()
      }
    }

    save(inputText)
  }

}
