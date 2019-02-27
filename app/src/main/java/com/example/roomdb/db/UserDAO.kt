package com.example.roomdb.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdb.db.entity.User


@Dao
interface UserDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("DELETE FROM User")
    fun deleteAll()

    @Query("SELECT * from User")
    fun getUsers() : LiveData<MutableList<User>>
}