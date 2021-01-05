package com.example.android.observability.di

import com.example.android.observability.retrofit.BlogRetrofit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideBlogService(retrofit: Retrofit):BlogRetrofit{
        return retrofit.create(BlogRetrofit::class.java)
    }

    @Singleton
    @Provides
    fun provideRetroift():Retrofit{
        return Retrofit.Builder()
                .baseUrl("https://open-api.xyz/placeholder/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(provideOkHttp3())
                .build()
    }

    private fun provideOkHttp3(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor {
                    val request = it.request().newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .method(it.request().method, it.request().body)
                            .build()
                    it.proceed(request)
                }
                .build()
    }


}