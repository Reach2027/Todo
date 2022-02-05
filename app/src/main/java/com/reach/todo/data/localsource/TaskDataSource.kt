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