package com.example.roomdb.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdb.db.entity.User

@Database(entities = [User::class], version = 1)
abstract class UserRoomDataBase : RoomDatabase() {

    internal abstract fun userDAO(): UserDAO

    companion object {

        @Volatile
        private var INSTANCE: UserRoomDataBase? = null


        internal fun getDatabase(context: Context): UserRoomDataBase? {
            if (INSTANCE == null) {
                synchronized(UserRoomDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            UserRoomDataBase::class.java, "user_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
