package com.example.roomdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.roomdb.db.UserDAO
import com.example.roomdb.db.UserRoomDataBase
import com.example.roomdb.db.entity.User
import com.example.roomdb.repository.UserDBRepository
import com.example.roomdb.repository.UserDBRepositoryImpl

class UserViewModel(context : Application) : AndroidViewModel(context) {

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
}