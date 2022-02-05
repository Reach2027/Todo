package com.reach.todo.repository

import com.reach.todo.data.entity.Task
import com.reach.todo.data.localsource.TaskDataSource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 2022/1/31  Reach
 *
 * todo 2022/1/31 : add network data source
 */
@Singleton
class TaskRepository @Inject constructor(private val localSource: TaskDataSource) {

    fun getTasks() = localSource.getTasks()

    fun getTaskFlow(uid: String) = localSource.getTaskFlow(uid)

    suspend fun getTask(uid: String) = localSource.getTask(uid)

    suspend fun add(task: Task) = localSource.insert(task)

    suspend fun update(task: Task) = localSource.update(task)

    suspend fun delete(task: Task) = localSource.delete(task)

}