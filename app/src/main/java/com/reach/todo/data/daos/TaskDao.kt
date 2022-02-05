package com.reach.todo.data.daos

import androidx.room.*
import com.reach.todo.data.entity.Task
import kotlinx.coroutines.flow.Flow

/**
 * 2022/1/29  Reach
 */
@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks ORDER BY editTime DESC")
    fun getTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE uid=:uid")
    fun getTaskFlow(uid: String): Flow<Task>

    @Query("SELECT * FROM tasks WHERE uid=:uid")
    suspend fun getTask(uid: String): Task

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task): Long

    @Update
    suspend fun update(task: Task): Int

    @Delete
    suspend fun delete(task: Task): Int

}