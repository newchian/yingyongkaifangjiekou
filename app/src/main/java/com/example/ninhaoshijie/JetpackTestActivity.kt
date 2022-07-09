package com.example.ninhaoshijie

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_jetpack_test.*
import kotlin.concurrent.thread

class JetpackTestActivity : AppCompatActivity() {
  lateinit var viewModel: MainViewModel
  lateinit var sp: SharedPreferences
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_jetpack_test)
    sp = getPreferences(Context.MODE_PRIVATE)
    val countReserved = sp.getInt("count_reserved", 0)
    viewModel = ViewModelProvider(this, MainViewModelFactory(countReserved)).get(MainViewModel::class.java)
    plusOneBtn.setOnClickListener {
      viewModel.plusOne()
    }
    clearBtn.setOnClickListener {
      viewModel.clear()
    }
    viewModel.counter.observe(this, Observer { count ->
      infoText.text = count.toString()
    })
    /*viewModel.counter.observe(this) { count ->
      infoText.text = count.toString()
    }*/
    /*viewModel.getUser("kk").observe(this,Observer { user ->
    })*/
    getUserBtn.setOnClickListener {
      val userId = (0..10000).random().toString()
      viewModel.getUser(userId)
    }
    viewModel.user.observe(this, Observer { user ->
      infoText.text = user.firstName
    })

    val userDao = AppDatabase.getDatabase(this).userDao()
    val user1 = User("Tom", "Brady", 40)
    val user2 = User("Tom", "Hanks", 63)
    addDataBtn.setOnClickListener {
      thread {
        user1.id = userDao.insertUser(user1)
        user2.id = userDao.insertUser(user2)
      }
    }
    updateDataBtn.setOnClickListener {
      thread {
        user1.age = 42
        userDao.updateUser(user1)
      }
    }
    deleteDataBtn.setOnClickListener {
      thread {
        userDao.deleteUserByLastName("Hanks")
      }
    }
    queryDataBtn.setOnClickListener {
      thread {
        for (user in userDao.loadAllUsers()) {
          Log.d("MainActivity", user.toString())
        }
      }
    }
    doWorkBtn.setOnClickListener {
      val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java).build()
      WorkManager.getInstance(this).enqueue(request)
    }

    lifecycle.addObserver(MyObserver(lifecycle))
  }
  override fun onPause() {
    super.onPause()
    sp.edit {
      putInt("count_reserved", viewModel.counter.value ?: 0)
    }
  }

}
