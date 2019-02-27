package com.example.roomdb.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "User")
data class User(
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "fullName") var fullName : String,
    @ColumnInfo(name = "age") var  age : Int?)