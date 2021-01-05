package com.example.android.observability.retrofit

import retrofit2.http.GET

interface BlogRetrofit {
    @GET("blogs")
    suspend fun get():List<BlogNetWorkEntity>

}