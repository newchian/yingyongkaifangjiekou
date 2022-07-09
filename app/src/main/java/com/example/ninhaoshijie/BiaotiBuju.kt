package com.example.ninhaoshijie

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.biaoti.view.*

class BiaotiBuju (context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
  init {
    LayoutInflater.from(context).inflate(R.layout.biaoti, this)
    titleBack.setOnClickListener {
      val activity = context as Activity
      activity.finish()
    }
    titleEdit.setOnClickListener {
      Toast.makeText(context, "您点击了编辑按钮", Toast.LENGTH_SHORT).show()
    }
  }
}