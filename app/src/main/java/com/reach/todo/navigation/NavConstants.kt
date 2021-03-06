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

package com.reach.todo.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryAddCheck
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.outlined.LibraryAddCheck
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PieChart
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * 2022/4/13  Reach
 */

const val ARGUMENT_TASK_ID = "taskId"

const val TASK_DETAIL_START = "TASK_DETAIL/"

const val EDIT_TASK_START = "EDIT_TASK/"

const val TITLE_TASKS = "To Do List"
const val TITLE_STATISTICS = "Statistics"
const val TITLE_YOU = "You"
const val TITLE_TASK_DETAIL = "To Do"
const val TITLE_EDIT_TASK = "Edit To Do"
const val TITLE_NEW_TASK = "New To Do"

object AppDestination {

    const val TASKS = "TASKS"

    const val STATISTICS = "STATISTICS"

    const val YOU = "YOU"

    const val TASK_DETAIL = "$TASK_DETAIL_START{$ARGUMENT_TASK_ID}"

    const val EDIT_TASK = "$EDIT_TASK_START{$ARGUMENT_TASK_ID}"

    const val NEW_TASK = "NEW_TASK"
}

enum class BottomSections(
    val route: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
) {

    TASKS(AppDestination.TASKS, Icons.Outlined.LibraryAddCheck, Icons.Filled.LibraryAddCheck),

    STATISTICS(AppDestination.STATISTICS, Icons.Outlined.PieChart, Icons.Filled.PieChart),

    YOU(AppDestination.YOU, Icons.Outlined.Person, Icons.Filled.Person)
}
