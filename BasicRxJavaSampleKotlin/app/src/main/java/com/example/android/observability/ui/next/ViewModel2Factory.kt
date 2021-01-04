package com.example.android.observability.ui.next

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.observability.persistence.next.User2Dao
import java.lang.IllegalArgumentException

class ViewModel2Factory(private val dataSource:User2Dao):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(User2ViewModel::class.java)){
            return User2ViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class ")
    }

}