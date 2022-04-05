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

package com.reach.todo.repository

import com.reach.todo.data.entity.Task
import com.reach.todo.data.localsource.TaskDataSource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 2022/1/31  Reach
 */
@Singleton
class DefaultTaskRepository @Inject constructor(private val localSource: TaskDataSource) :
    TaskRepository {

    override fun getTasks() = localSource.getTasks()

    override fun getTask(uid: String) = localSource.getTaskFlow(uid)

    override suspend fun add(task: Task) = localSource.insert(task)

    override suspend fun update(task: Task) = localSource.update(task)

    override suspend fun delete(task: Task) = localSource.delete(task)
}
