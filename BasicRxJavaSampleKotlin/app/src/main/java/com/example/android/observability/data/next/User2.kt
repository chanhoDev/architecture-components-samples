package com.example.android.observability.data.next

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "users2")
data class User2 (
        @PrimaryKey
        @ColumnInfo(name = "user2id")
        val id :String = UUID.randomUUID().toString(),

        @ColumnInfo(name = "user2name")
        val userName:String)
