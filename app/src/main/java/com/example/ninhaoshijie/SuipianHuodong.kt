package com.example.ninhaoshijie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.left_fragment.*
import kotlinx.android.synthetic.main.suipian_buju.*

class SuipianHuodong : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.suipian_buju)
    val fragment = leftFrag as LeftFragment
    fun replaceFragment(fragment: Fragment) {
      /*val fragmentManager = supportFragmentManager
      val transaction = fragmentManager.beginTransaction()
      transaction.replace(R.id.rightLayout, fragment)
      transaction.addToBackStack(null)
      transaction.commit()*/
    }
    tihuanSuipianAnniu.setOnClickListener {
      replaceFragment(AnotherRightFragment())
    }
    replaceFragment(RightFragment())
  }
}
