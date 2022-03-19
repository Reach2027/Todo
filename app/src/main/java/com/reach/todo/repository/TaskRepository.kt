package com.reach.todo.repository

import com.reach.todo.data.entity.Task
import kotlinx.coroutines.flow.Flow

/**
 * 2022/3/17  Reach
 */
interface TaskRepository {

    fun getTasks(): Flow<List<Task>>

    fun getTask(uid: String): Flow<Task>

    suspend fun add(task: Task): Long

    suspend fun update(task: Task): Int

    suspend fun delete(task: Task): Int

}