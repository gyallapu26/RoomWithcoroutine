package com.example.roomdb.db.entity

import androidx.annotation.Nullable
import androidx.room.*
import org.jetbrains.annotations.NotNull


@Entity(tableName = "User")
data class User(
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "fullName") var fullName : String,
    @ColumnInfo(name = "age") var  age : Int?,
    @Nullable
    var gender: String?,
    var isDoctor: Boolean? =  false)