package com.example.roomdb.repository

import androidx.lifecycle.LiveData
import com.example.roomdb.db.UserDAO
import com.example.roomdb.db.entity.User
import kotlinx.coroutines.*

class UserDBRepositoryImpl(private val userDAO: UserDAO) :
    UserDBRepository {


    override suspend fun getUsers(): LiveData<MutableList<User>>  {

        return GlobalScope.async { userDAO.getUsers() }.await()

    }

    override fun insert(user: User) {
        GlobalScope.launch {
            userDAO.insert(user)
        }
    }

    override fun deleteAll() {
        GlobalScope.launch {
            userDAO.deleteAll()
        }
    }
}