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

import com.reach.commonkt.di.IoDispatcher
import com.reach.datalayer.local.daos.TaskDao
import com.reach.datalayer.local.entity.Task
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * 2022/1/31  Reach
 */
@Singleton
class DefaultTaskLocalDataSource @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val taskDao: TaskDao
) : TaskLocalDataSource {

    override fun getTasks() = taskDao.getTasks()

    override fun getTask(uid: String) = taskDao.getTask(uid)

    override suspend fun insert(task: Task) = withContext(dispatcher) { taskDao.insert(task) }

    override suspend fun update(task: Task) = withContext(dispatcher) { taskDao.update(task) }

    override suspend fun delete(task: Task) = withContext(dispatcher) { taskDao.delete(task) }
}
