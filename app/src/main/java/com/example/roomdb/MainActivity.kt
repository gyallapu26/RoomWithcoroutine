package com.example.roomdb

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.roomdb.databinding.ActivityMainBinding
import com.example.roomdb.db.entity.User
import com.example.roomdb.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)


        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)?.apply {
            this.lifecycleOwner = this@MainActivity
            this.viewModel = userViewModel
        }

        subscribeUI()
    }

    private fun subscribeUI() {

        runBlocking {
            launch {
                userViewModel.getUsers()?.observe(this@MainActivity, Observer {
                    Toast.makeText(this@MainActivity, "List is $it", Toast.LENGTH_SHORT).show()
                    Log.d("sos", "users are $it")
                })
            }
        }

    }

}
