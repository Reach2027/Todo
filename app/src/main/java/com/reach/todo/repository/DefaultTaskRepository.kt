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