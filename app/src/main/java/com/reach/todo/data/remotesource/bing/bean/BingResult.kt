package com.reach.todo.data.remotesource.bing.bean

import com.squareup.moshi.JsonClass

/**
 * 2022/2/5  Reach
 */
@JsonClass(generateAdapter = true)
data class BingResult(val images: List<BingImage>)

@JsonClass(generateAdapter = true)
data class BingImage(
    val url: String = "",
    val copyright: String = "",
    val title: String = ""
)