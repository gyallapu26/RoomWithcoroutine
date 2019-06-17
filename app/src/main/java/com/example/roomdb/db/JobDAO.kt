package com.example.roomdb.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdb.db.entity.Job
import com.example.roomdb.db.entity.User


@Dao
interface JobDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(job: Job)

    @Query("DELETE FROM User")
    fun deleteAll()

    @Query("SELECT * from User")
    fun getJobs() : LiveData<MutableList<User>>
}