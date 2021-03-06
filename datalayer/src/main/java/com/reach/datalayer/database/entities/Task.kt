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

package com.reach.datalayer.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar
import java.util.UUID

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
