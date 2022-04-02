/*
 * Copyright 2022 The Android Open Source Project
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

package com.reach.todo.data.localsource

import com.reach.todo.data.daos.TaskDao
import com.reach.todo.data.entity.Task
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 2022/1/31  Reach
 */
@Singleton
class TaskDataSource @Inject constructor(private val taskDao: TaskDao) {

    fun getTasks() = taskDao.getTasks()

    fun getTaskFlow(uid: String) = taskDao.getTaskFlow(uid)

    suspend fun getTask(uid: String) = taskDao.getTask(uid)

    suspend fun insert(task: Task) = taskDao.insert(task)

    suspend fun update(task: Task) = taskDao.update(task)

    suspend fun delete(task: Task) = taskDao.delete(task)
}
