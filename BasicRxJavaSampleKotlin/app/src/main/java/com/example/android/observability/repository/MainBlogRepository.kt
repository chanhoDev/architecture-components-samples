package com.example.android.observability.repository

import com.example.android.observability.data.blog.Blog
import com.example.android.observability.data.blog.BlogDao
import com.example.android.observability.data.blog.CacheMapper
import com.example.android.observability.data.next.User2
import com.example.android.observability.data.next.User2Dao
import com.example.android.observability.retrofit.BlogRetrofit
import com.example.android.observability.retrofit.NetworkMapper
import com.example.android.observability.util.DataState
import io.reactivex.Completable
import io.reactivex.Flowable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainBlogRepository constructor(
        private val blogDao: BlogDao,
        private val blogRetrofit: BlogRetrofit,
        private val cacheMapper: CacheMapper,
        private val networkMapper: NetworkMapper){
    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val netWorkBlogs = blogRetrofit.get()
            var blogs = networkMapper.mapFromEntityList(netWorkBlogs)

            for(blog in blogs){
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e:Exception){
            emit(DataState.Error(e))
        }
    }
}
