package com.reach.todo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryAddCheck
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.outlined.LibraryAddCheck
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PieChart
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * 2022/1/29  Reach
 */

const val FILTER_ALL = 0
const val FILTER_ACTIVE = 1
const val FILTER_COMPLETED = 2

const val DATABASE_NAME = "reach_db"

const val TASK_ID_ARGUMENT = "taskId"

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

    const val TASK_DETAIL = "$TASK_DETAIL_START{$TASK_ID_ARGUMENT}"

    const val EDIT_TASK = "$EDIT_TASK_START{$TASK_ID_ARGUMENT}"

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