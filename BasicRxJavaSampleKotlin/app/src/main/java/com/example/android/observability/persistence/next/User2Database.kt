package com.example.android.observability.persistence.next

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User2::class], version = 1)
abstract class User2Database : RoomDatabase() {

    abstract fun user2Dao(): User2Dao

    companion object {
        @Volatile
        private var INSTANCE: User2Database? = null

        fun getInstance(context: Context): User2Database {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): User2Database {
            return Room.databaseBuilder(context.applicationContext,
                    User2Database::class.java, "Sample2.db")
                    .build()
        }

    }
}