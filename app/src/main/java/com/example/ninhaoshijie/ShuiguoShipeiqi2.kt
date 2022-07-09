package com.example.ninhaoshijie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.shuiguo_zixiang.view.*

class ShuiguoShipeiqi2(val fruitList: List<Shuiguo>) :
  RecyclerView.Adapter<ShuiguoShipeiqi2.ViewHolder>() {
  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val fruitImage: ImageView = view.fruitImage
    val fruitName: TextView = view.fruitName
  }
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.shuiguo_zixiang, parent, false)
    return ViewHolder(view)
  }
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val fruit = fruitList[position]
    holder.fruitImage.setImageResource(fruit.imageId)
    holder.fruitName.text = fruit.name
  }
  override fun getItemCount() = fruitList.size
}