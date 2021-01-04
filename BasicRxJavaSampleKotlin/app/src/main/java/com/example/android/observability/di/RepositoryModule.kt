package com.example.android.observability.di

import android.content.Context
import androidx.room.Room
import com.example.android.observability.persistence.next.User2Dao
import com.example.android.observability.persistence.next.User2Database
import com.example.android.observability.persistence.next.User2Database.Companion.DATABASE_2_NAME
import com.example.android.observability.persistence.user.UsersDatabase
import com.example.android.observability.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
            dataSource: User2Dao
    ):MainRepository{
        return MainRepository(dataSource)
    }
}