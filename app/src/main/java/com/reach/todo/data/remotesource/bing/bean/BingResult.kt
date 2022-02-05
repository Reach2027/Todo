package com.reach.todo.data.remotesource.bing.bean

/**
 * 2022/2/5  Reach
 */
data class BingResult(val images: List<BingImage>)

data class BingImage(
    val url: String = "",
    val copyright: String = "",
    val title: String = ""
)