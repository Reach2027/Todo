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

import com.reach.datalayer.local.task.DefaultTaskLocalDataSource
import com.reach.datalayer.local.task.TaskLocalDataSource
import com.reach.datalayer.repository.DefaultTaskRepository
import com.reach.datalayer.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * 2022/3/17  Reach
 */
@InstallIn(SingletonComponent::class)
@Module
abstract class TaskDataModule {

    @Singleton
    @Binds
    abstract fun bindTaskLocalTaskSource(localDataSource: DefaultTaskLocalDataSource)
        : TaskLocalDataSource

    @Singleton
    @Binds
    abstract fun bindTaskRepository(taskLocalDataSource: DefaultTaskRepository)
        : TaskRepository
}
