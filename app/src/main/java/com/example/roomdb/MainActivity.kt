package com.example.roomdb

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.roomdb.db.entity.User
import com.example.roomdb.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)


        runBlocking {
            launch {
                userViewModel.getUsers()?.observe(this@MainActivity, Observer {
                    Toast.makeText(this@MainActivity, "List is $it", Toast.LENGTH_SHORT).show()
                    Log.d("sos", "users are $it")
                })
            }
        }
        submit_button?.setOnClickListener {
            if (isInputValid()){
                val user : User = formUserObj()
                insertUser(user)
            }
        }

    }



    private fun isInputValid(): Boolean {
        var isValid = true
        if (fullNameInputLayout.editText?.text?.isEmpty()!!){
            isValid = false
            fullNameInputLayout.editText?.error = "Name shouldn't be empty !"
        }
        if (ageInputLayout.editText?.text?.isEmpty()!!){
            isValid = false
            ageInputLayout.editText?.error = "Age shouldn't be empty"
        }

        return isValid
    }

    private fun insertUser(user: User) {

        userViewModel.insertUser(user)

    }

    private fun formUserObj(): User {

        return User(
            fullNameInputLayout.editText?.text.toString(),
            ageInputLayout.editText?.text.toString().toInt()
        )
    }
}
