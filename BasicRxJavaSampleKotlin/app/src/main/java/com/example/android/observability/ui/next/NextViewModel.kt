package com.example.android.observability.ui.next

import androidx.lifecycle.ViewModel
import com.example.android.observability.persistence.next.User2
import com.example.android.observability.persistence.next.User2Dao
import com.example.android.observability.persistence.user.UserDao
import io.reactivex.Completable
import io.reactivex.Flowable

class NextViewModel(private val dataSource: User2Dao): ViewModel() {

    fun userName(): Flowable<String> {
        return dataSource.getUserById("1")
                .map { user -> user.userName }
    }

    fun updateUserName(userName:String):Completable{
        val user = User2("1",userName)
        return dataSource.insertUser(user)
    }
}