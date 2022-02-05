package com.reach.todo.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * 2022/1/29  Reach
 */
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey val uid: String = UUID.randomUUID().toString(),
    val createTime: Calendar = Calendar.getInstance(),
    var content: String = "",
    var finished: Boolean = false,
    var editTime: Calendar = Calendar.getInstance()
)