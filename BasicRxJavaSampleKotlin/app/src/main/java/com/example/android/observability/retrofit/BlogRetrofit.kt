package com.example.android.observability.retrofit

import com.example.android.observability.data.blog.Blog
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface BlogRetrofit {
    @GET("blogs")
    fun getBlogs(): Single<Response<List<Blog>>>

}