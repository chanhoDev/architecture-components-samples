/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.observability

import android.content.Context
import com.example.android.observability.persistence.next.User2Dao
import com.example.android.observability.persistence.next.User2Database
import com.example.android.observability.persistence.user.UserDao
import com.example.android.observability.persistence.user.UsersDatabase
import com.example.android.observability.ui.next.ViewModel2Factory
import com.example.android.observability.ui.user.ViewModelFactory

/**
 * Enables injection of data sources.
 */
object Injection {


    fun proivideViewModel2Factory(user2Dao:User2Dao):ViewModel2Factory{
        return ViewModel2Factory(user2Dao)
    }

    fun provideUserDataSource(context: Context): UserDao {
        val database = UsersDatabase.getInstance(context)
        return database.userDao()
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSource = provideUserDataSource(context)
        return ViewModelFactory(dataSource)
    }
}
