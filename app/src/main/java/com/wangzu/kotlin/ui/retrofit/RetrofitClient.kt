package com.wangzu.kotlin.ui.retrofit

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by AnJiuZhe on 2018/6/6 0006.
 * Email 1573335865@qq.com
 */
class RetrofitClient private constructor() {

    private val apiService: ApiService

    init {
        val client = OkHttpClient.Builder().build()
        val baseUrl = ApiService.baseUrl
        val retrofit = Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun getApiService() = apiService

    internal object Holder {
        var intance = RetrofitClient()
    }

    companion object {

        val instance: RetrofitClient
            get() = Holder.intance
    }
}
