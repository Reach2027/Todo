/*
 * Copyright 2022 Reach2027
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.reach.datalayer.remote.bing

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
        @Query("n") n: Int = 8,
        @Query("ensearch") en: Int = 1
    ): BingResult
}
