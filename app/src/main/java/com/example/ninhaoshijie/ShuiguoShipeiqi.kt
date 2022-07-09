package com.example.ninhaoshijie

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.shuiguo_zixiang.view.*

class ShuiguoShipeiqi(activity: Activity, val resourceId: Int, data: List<Shuiguo>) :
  ArrayAdapter<Shuiguo>(activity, resourceId, data) {
  inner class ViewHolder(val fruitImage: ImageView, val fruitName: TextView)
  override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    val view : View
    val viewHolder: ViewHolder
    if (convertView == null) {
      view = LayoutInflater.from(context).inflate(resourceId, parent, false)
      val fruitImage: ImageView = view.fruitImage
      val fruitName: TextView = view.fruitName
      viewHolder = ViewHolder(fruitImage, fruitName)
      view.tag = viewHolder
    } else {
      view = convertView
      viewHolder = view.tag as ViewHolder
    }
    val fruit = getItem(position) // 获取当前项的Fruit实例
    if (fruit != null) {
      viewHolder.fruitImage.setImageResource(fruit.imageId)
      viewHolder.fruitName.text = fruit.name
    }
    return view
  }
}