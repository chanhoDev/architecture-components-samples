package com.example.android.observability.di

import com.example.android.observability.data.blog.BlogDao
import com.example.android.observability.data.blog.CacheMapper
import com.example.android.observability.data.next.User2Dao
import com.example.android.observability.repository.MainBlogRepository
import com.example.android.observability.repository.MainRepository
import com.example.android.observability.retrofit.BlogRetrofit
import com.example.android.observability.retrofit.NetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
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

    @Singleton
    @Provides
    fun provideBlogMainRepository(
            blogDao:BlogDao,
            blogRetrofit:BlogRetrofit,
            cachMapper: CacheMapper,
            networkMapper: NetworkMapper):MainBlogRepository{
        return MainBlogRepository(blogDao,blogRetrofit,cachMapper,networkMapper)
    }
}