package com.example.ninhaoshijie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.msg_left_item.view.*
import kotlinx.android.synthetic.main.msg_right_item.view.*

class MsgAdapter(val msgList: List<Msg>) : RecyclerView.Adapter<MsgViewHolder>() {
  override fun getItemViewType(position: Int): Int {
    val msg = msgList[position]
    return msg.type
  }
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = if (viewType ==
    Msg.TYPE_RECEIVED) {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item,
      parent, false)
    LeftViewHolder(view)
  } else {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item,
      parent, false)
    RightViewHolder(view)
  }
  override fun onBindViewHolder(holder: MsgViewHolder, position: Int) {
    val msg = msgList[position]
    when (holder) {
      is LeftViewHolder -> holder.leftMsg.text = msg.content
      is RightViewHolder -> holder.rightMsg.text = msg.content
//      is MiddleViewHolder -> "中间视图持有者"
      /*else -> throw IllegalArgumentException()*/
    }
  }
  override fun getItemCount() = msgList.size
}

sealed class MsgViewHolder(view: View) : RecyclerView.ViewHolder(view)

class LeftViewHolder(view: View) : MsgViewHolder(view) {
  val leftMsg: TextView = view.findViewById(R.id.leftMsg)
}

class RightViewHolder(view: View) : MsgViewHolder(view) {
  val rightMsg: TextView = view.findViewById(R.id.rightMsg)
}

class MiddleViewHolder(view: View) : MsgViewHolder(view) {

}



