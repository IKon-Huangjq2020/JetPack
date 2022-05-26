package com.kotlin.pagingdbnet.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object NetworkService {

    private val retrofit = Retrofit.Builder()
        .client(
            OkHttpClient.Builder()
                .callTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()
        )
        .baseUrl("https://app.api.sj.360.cn/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val rankAppListApi = retrofit.create<RankAppListApi>()

}