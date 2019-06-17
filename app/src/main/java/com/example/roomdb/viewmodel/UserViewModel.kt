package com.example.roomdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roomdb.db.UserDAO
import com.example.roomdb.db.UserRoomDataBase
import com.example.roomdb.db.entity.Gender
import com.example.roomdb.db.entity.Job
import com.example.roomdb.db.entity.User
import com.example.roomdb.repository.UserDBRepository
import com.example.roomdb.repository.UserDBRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*

class UserViewModel(context : Application) : AndroidViewModel(context) {

     var userNameLiveData : MutableLiveData<String> = MutableLiveData()
     var ageLiveData : MutableLiveData<String> = MutableLiveData()


    private lateinit var userDAO: UserDAO
    private var userDBRepositoryImpl: UserDBRepository

    init {
        UserRoomDataBase.getDatabase(context)?.userDAO()?.let {
            userDAO = it
        }
        userDBRepositoryImpl = UserDBRepositoryImpl(userDAO)

    }
     fun insertUser(user: User){
        userDBRepositoryImpl.insert(user)
    }

     suspend fun getUsers() : LiveData<MutableList<User>>? {

        return  userDBRepositoryImpl.getUsers()
    }


    fun deleteAll(){
        userDBRepositoryImpl.deleteAll()
    }

    fun done(){
        if (isInputValid()){
            val user : User = formUserObj()
            insertUser(user)
        }

    }

    private fun formUserObj(): User {

        return User(
            userNameLiveData.value.toString(),
            ageLiveData.value.toString().toInt(),
            gender = "F"
        )
    }

    private fun isInputValid(): Boolean {
        var isValid = true
        if (userNameLiveData.value?.isEmpty()!!){
            isValid = false
           // fullNameInputLayout.editText?.error = "Name shouldn't be empty !"
        }
        if (ageLiveData.value?.isEmpty()!!){
            isValid = false
            //ageInputLayout.editText?.error = "Age shouldn't be empty"
        }

        return isValid
    }
}