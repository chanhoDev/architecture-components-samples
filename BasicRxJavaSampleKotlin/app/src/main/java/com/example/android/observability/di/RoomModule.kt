package com.example.android.observability.di

import android.content.Context
import androidx.room.Room
import com.example.android.observability.data.blog.BlogDao
import com.example.android.observability.data.blog.BlogDatabase
import com.example.android.observability.data.blog.BlogDatabase.Companion.DATABASE_NAME
import com.example.android.observability.data.next.User2Database
import com.example.android.observability.data.next.User2Database.Companion.DATABASE_2_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun build2Database(@ApplicationContext context: Context) =
            Room.databaseBuilder(context,
                    User2Database::class.java,
                    DATABASE_2_NAME)
                    .fallbackToDestructiveMigration()
                    .build()

    @Singleton
    @Provides
    fun provideUser2Dao(user2Database: User2Database) = user2Database.user2Dao()


    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): BlogDatabase {
        return Room.databaseBuilder(context,
                BlogDatabase::class.java,
                DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }


    @Singleton
    @Provides
    fun provideBlogDAO(blogDatabase: BlogDatabase): BlogDao {
        return blogDatabase.blogDao()
    }
}