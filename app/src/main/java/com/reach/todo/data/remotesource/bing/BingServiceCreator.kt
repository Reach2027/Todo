package com.reach.todo.data.remotesource.bing

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 2022/2/5  Reach
 */

@Singleton
class BingServiceCreator @Inject constructor() {

    companion object {
        const val BASE_URL = "https://cn.bing.com"
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)

}