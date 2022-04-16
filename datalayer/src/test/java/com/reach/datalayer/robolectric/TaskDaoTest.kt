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

package com.reach.datalayer.robolectric

import android.app.Application
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.reach.datalayer.database.daos.TaskDao
import com.reach.datalayer.database.entities.Task
import com.reach.datalayer.local.database.AppDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.hasItems
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * 2022/3/12  Reach
 */
@RunWith(AndroidJUnit4::class)
class TaskDaoTest {

    private lateinit var database: AppDatabase

    private lateinit var taskDao: TaskDao

    @Before
    fun createDb() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Application>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        taskDao = database.taskDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetTasks() = runBlocking {
        val task1 = insert("task1")
        val task2 = insert("task2")

        val tasks = taskDao.getTasks().first()
        assertEquals(2, tasks.size)
        assertThat(tasks, hasItems(task1, task2))
    }

    @Test
    fun testGetTask() = runBlocking {
        val task = insert("testGetTask")

        val dbTask = taskDao.getTask(task.uid).first()
        assertEquals(task, dbTask)
    }

    @Test
    fun testUpdate() = runBlocking {
        val task = insert("testUpdate")

        val updateCount = taskDao.update(task.copy(content = "update"))
        assertEquals(1, updateCount)

        val dbTask = taskDao.getTask(task.uid).first()
        assertEquals("update", dbTask.content)
    }

    @Test
    fun testDelete() = runBlocking {
        val task = insert("testDelete")

        val deleteCount = taskDao.delete(task)
        assertEquals(1, deleteCount)

        val tasks = taskDao.getTasks().first()
        assertEquals(0, tasks.size)
    }

    private suspend fun insert(content: String): Task {
        val task = Task(content = content)
        taskDao.insert(task)
        return task
    }
}
