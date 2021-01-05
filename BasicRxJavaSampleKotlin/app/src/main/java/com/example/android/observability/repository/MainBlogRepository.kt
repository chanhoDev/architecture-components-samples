package com.example.android.observability.repository

import com.example.android.observability.data.blog.Blog
import com.example.android.observability.data.blog.BlogDao
import com.example.android.observability.data.blog.CacheMapper
import com.example.android.observability.retrofit.BlogRetrofit
import io.reactivex.Single
import retrofit2.Response

class MainBlogRepository constructor(
        private val blogDao: BlogDao,
        private val blogRetrofit: BlogRetrofit) {

    fun getBlog(): Single<Response<List<Blog>>> {
        return blogRetrofit.getBlogs()
    }


}
