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

package com.reach.datalayer.remote

import com.reach.datalayer.remote.bing.BingResult
import com.reach.datalayer.remote.bing.BingService
import com.reach.datalayer.remote.bing.BingServiceCreator
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * 2022/2/5  Reach
 */
@Singleton
class BingRemoteDataSource @Inject constructor(
    private val serviceCreator: BingServiceCreator
) {

    private val bingService by lazy {
        serviceCreator.create<BingService>()
    }

    suspend fun getImageUrl(): Flow<BingResult> = flow {
        emit(bingService.getImageUrl())
    }.flowOn(Dispatchers.IO)
        .catch { e ->
            e.printStackTrace()
        }
}
