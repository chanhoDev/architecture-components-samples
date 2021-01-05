package com.example.android.observability.data.next

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface User2Dao {
    @Query("SELECT * FROM Users2 WHERE user2id = :id")
    fun getUserById(id:String):Flowable<User2>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user2:User2):Completable

    @Query("DELETE FROM users2")
    fun deleteAllUsers()
}