package com.reach.todo.data.remotesource.bing

import com.reach.todo.data.remotesource.bing.bean.BingResult
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 2022/2/5  Reach
 */
interface BingService {

    @GET("/HPImageArchive.aspx")
    suspend fun getImageUrl(
        @Query("format") format: String = "js",
        @Query("idx") idx: Int = 0,
        @Query("n") n: Int = 1,
        @Query("mkt") mkt: String = "zh-CN"
    ): BingResult

}