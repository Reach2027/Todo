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

package com.reach.datalayer.di

import com.reach.commonkt.RetrofitServiceCreator
import com.reach.commonkt.di.IoDispatcher
import com.reach.datalayer.BING_BASE_URL
import com.reach.datalayer.remote.bing.BingRemoteDataSource
import com.reach.datalayer.remote.bing.DefaultBingRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

/**
 * 2022/4/16  Reach
 */
@InstallIn(SingletonComponent::class)
@Module
object BingDataModule {

    @Singleton
    @Provides
    fun provideBingRemoteDataSource(
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): BingRemoteDataSource {
        return DefaultBingRemoteDataSource(dispatcher, RetrofitServiceCreator(BING_BASE_URL))
    }
}
