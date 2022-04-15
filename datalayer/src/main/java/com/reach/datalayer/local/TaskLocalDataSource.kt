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

package com.reach.datalayer.local

import com.reach.datalayer.local.entity.Task
import kotlinx.coroutines.flow.Flow

/**
 * 2022/4/15  Reach
 */
interface TaskLocalDataSource {

    fun getTasks(): Flow<List<Task>>

    fun getTask(uid: String): Flow<Task?>

    suspend fun insert(task: Task): Long

    suspend fun update(task: Task): Int

    suspend fun delete(task: Task): Int
}
