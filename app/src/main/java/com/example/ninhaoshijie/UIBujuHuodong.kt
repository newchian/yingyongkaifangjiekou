package com.example.ninhaoshijie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.uibuju_buju.*

class UIBujuHuodong : AppCompatActivity(), View.OnClickListener {
  override fun onClick(v: View?) {
    when (v) {
      send -> {
        val content = inputText.text.toString()
        if (content.isNotEmpty()) {
          val msg = Msg(content, Msg.TYPE_SENT)
          msgList.add(msg)
          adapter.notifyItemInserted(msgList.size - 1) // 当有新消息时， 刷新RecyclerView中的显示
          recyclerView.scrollToPosition(msgList.size - 1) // 将RecyclerView 定位到最后一行
          inputText.setText("") // 清空输入框中的内容
        }
      }
    }
  }

  private val fruitList = ArrayList<Shuiguo>()
  private val msgList = ArrayList<Msg>()
  private lateinit var adapter: MsgAdapter
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.uibuju_buju)

    fun xianshiLiebiaoShitu(kegundongde: ListView) {
      initFruits()
      kegundongde.adapter = ShuiguoShipeiqi(this, R.layout.shuiguo_zixiang, fruitList)
      kegundongde.setOnItemClickListener { _, _, position, _ ->
        val fruit = fruitList[position]
        Toast.makeText(this, fruit.name, Toast.LENGTH_SHORT).show()
      }
    }

    fun xianshiRecyclerView(kegundongde: RecyclerView, orientation: Int = LinearLayoutManager.VERTICAL) {
      initFruits()
      val layoutManager = LinearLayoutManager(this)
      layoutManager.orientation = orientation
      kegundongde.layoutManager = layoutManager
      kegundongde.adapter = ShuiguoShipeiqi2(fruitList)
    }

    fun xianshiWanggeRecyclerView(kegundongde: RecyclerView) {
      initFruits()
      val layoutManager = GridLayoutManager(this,2)
      kegundongde.layoutManager = layoutManager
      kegundongde.adapter = ShuiguoShipeiqi2(fruitList)
    }

    fun getRandomLengthString(str: String): String {
      val n = (1..20).random()
      val builder = StringBuilder()
      repeat(n) {
        builder.append(str)
      }
      return builder.toString()
    }

    fun initFruitsForStaggeredGridLayoutManager() {
      repeat(2) {
        fruitList.add(Shuiguo(getRandomLengthString("Apple"),
          R.drawable.apple_pic))
        fruitList.add(Shuiguo(getRandomLengthString("Banana"),
          R.drawable.banana_pic))
        fruitList.add(Shuiguo(getRandomLengthString("Orange"),
          R.drawable.orange_pic))
        fruitList.add(Shuiguo(getRandomLengthString("Watermelon"),
          R.drawable.watermelon_pic))
        fruitList.add(Shuiguo(getRandomLengthString("Pear"),
          R.drawable.pear_pic))
        fruitList.add(Shuiguo(getRandomLengthString("Grape"),
          R.drawable.grape_pic))
        fruitList.add(Shuiguo(getRandomLengthString("Pineapple"),
          R.drawable.pineapple_pic))
        fruitList.add(Shuiguo(getRandomLengthString("Strawberry"),
          R.drawable.strawberry_pic))
        fruitList.add(Shuiguo(getRandomLengthString("Cherry"),
          R.drawable.cherry_pic))
        fruitList.add(Shuiguo(getRandomLengthString("Mango"),
          R.drawable.mango_pic))
      }
    }

    fun xianshiStaggeredGridRecyclerView(kegundongde: RecyclerView) {
      initFruitsForStaggeredGridLayoutManager()
      val layoutManager = StaggeredGridLayoutManager(3,
        StaggeredGridLayoutManager.VERTICAL)
      kegundongde.layoutManager = layoutManager
      kegundongde.adapter = ShuiguoShipeiqi2(fruitList)
    }

    fun initMsg() {
      msgList.add(Msg("您好小家伙。", Msg.TYPE_RECEIVED))
      msgList.add(Msg("您好。那人是谁？", Msg.TYPE_SENT))
      msgList.add(Msg("那是汤姆。很高兴和您谈话。", Msg.TYPE_RECEIVED))
    }

    fun xianshiLiaotianXiaoxiRecyclerView(kegundongde: RecyclerView) {
      initMsg()
      kegundongde.layoutManager = LinearLayoutManager(this)
      if(!::adapter.isInitialized)adapter = MsgAdapter(msgList)
      kegundongde.adapter = adapter
      send.setOnClickListener(this)
    }
//    xianshiLiebiaoShitu(listView)
//    xianshiRecyclerView(recyclerView,LinearLayoutManager.HORIZONTAL)
//    xianshiWanggeRecyclerView(recyclerView)
//    xianshiStaggeredGridRecyclerView(recyclerView)
    xianshiLiaotianXiaoxiRecyclerView(recyclerView)
  }
  private fun initFruits() {
    repeat(2) {
      fruitList.add(Shuiguo("Apple", R.drawable.apple_pic))
      fruitList.add(Shuiguo("Banana", R.drawable.banana_pic))
      fruitList.add(Shuiguo("Orange", R.drawable.orange_pic))
      fruitList.add(Shuiguo("Watermelon", R.drawable.watermelon_pic))
      fruitList.add(Shuiguo("Pear", R.drawable.pear_pic))
      fruitList.add(Shuiguo("Grape", R.drawable.grape_pic))
      fruitList.add(Shuiguo("Pineapple", R.drawable.pineapple_pic))
      fruitList.add(Shuiguo("Strawberry", R.drawable.strawberry_pic))
      fruitList.add(Shuiguo("Cherry", R.drawable.cherry_pic))
      fruitList.add(Shuiguo("Mango", R.drawable.mango_pic))
    }
  }
}
