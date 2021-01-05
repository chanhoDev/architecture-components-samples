package com.example.android.observability.repository

import com.example.android.observability.data.next.User2
import com.example.android.observability.data.next.User2Dao
import io.reactivex.Completable
import io.reactivex.Flowable

class MainRepository constructor(
        private val user2Dao:User2Dao
){
    fun getUserById(id:String): Flowable<User2>{
        return user2Dao.getUserById(id)
    }
    fun setUserName(user2:User2):Completable{
        return user2Dao.insertUser(user2)
    }
}
