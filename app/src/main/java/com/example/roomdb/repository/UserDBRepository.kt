package com.example.roomdb.repository

import androidx.lifecycle.LiveData
import com.example.roomdb.db.entity.User

interface UserDBRepository {

     suspend fun getUsers() : LiveData<MutableList<User>>?

    fun insert(user: User)

    fun deleteAll()
}