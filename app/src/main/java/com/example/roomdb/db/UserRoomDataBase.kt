package com.example.roomdb.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomdb.db.entity.User

@Database(entities = [User::class], version = 3)
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
                        ).addMigrations(MIGRATION_2_3)
                            .build()
                    }
                }
            }
            return INSTANCE
        }



        private var MIGRATION_2_3 : Migration = object : Migration(2,3){

            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'User' ADD COLUMN 'isDoctor' INTEGER DEFAULT 0");
            }

        }
    }
}
