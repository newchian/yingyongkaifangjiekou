package com.example.ninhaoshijie

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyObserver(val lifecycle: Lifecycle) : LifecycleObserver {
  @OnLifecycleEvent(Lifecycle.Event.ON_START)
  fun activityStart() {
    Log.d("MyObserver", "activityStart 当前状态：${lifecycle.currentState}")
  }
  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  fun activityStop() {
    Log.d("MyObserver", "activityStop 当前状态：${lifecycle.currentState}")
  }

}