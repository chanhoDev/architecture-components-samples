package com.example.android.observability.data.next

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User2::class], version = 1)
abstract class User2Database : RoomDatabase() {

    abstract fun user2Dao(): User2Dao

    companion object {
        val DATABASE_2_NAME = "Sample2.db"
    }
}