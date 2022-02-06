package com.reach.todo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.reach.todo.AppDestination
import com.reach.todo.EDIT_TASK_START
import com.reach.todo.TASK_DETAIL_START
import com.reach.todo.TASK_ID_ARGUMENT
import com.reach.todo.ui.edittask.EditTaskScreen
import com.reach.todo.ui.edittask.EditTaskViewModel
import com.reach.todo.ui.statistics.StatisticsScreen
import com.reach.todo.ui.statistics.StatisticsViewModel
import com.reach.todo.ui.taskdetail.TaskDetailScreen
import com.reach.todo.ui.taskdetail.TaskDetailViewModel
import com.reach.todo.ui.tasks.TasksScreen
import com.reach.todo.ui.tasks.TasksViewModel
import com.reach.todo.ui.you.YouScreen
import com.reach.todo.ui.you.YouViewModel

/**
 * 2022/1/30  Reach
 */
@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppDestination.STATISTICS,
        modifier = modifier
    ) {
        composable(AppDestination.TASKS) {
            val tasksViewModel = hiltViewModel<TasksViewModel>()
            TasksScreen(
                tasksViewModel = tasksViewModel,
                navTaskDetail = { taskId ->
                    navController.navigate("$TASK_DETAIL_START$taskId")
                },
                navAddTask = { navController.navigate(AppDestination.NEW_TASK) }
            )
        }
        composable(AppDestination.STATISTICS) {
            val statisticsViewModel = hiltViewModel<StatisticsViewModel>()
            StatisticsScreen(statisticsViewModel)
        }
        composable(AppDestination.YOU) {
            val youViewModel = hiltViewModel<YouViewModel>()
            YouScreen(youViewModel)
        }

        composable(
            route = AppDestination.TASK_DETAIL,
            arguments = listOf(navArgument(TASK_ID_ARGUMENT) { type = NavType.StringType })
        ) { entry ->
            val taskId = entry.arguments?.getString(TASK_ID_ARGUMENT)
            if (taskId.isNullOrEmpty()) {
                throw IllegalArgumentException("TaskDetail arguments error")
            }
            val taskDetailViewModel = hiltViewModel<TaskDetailViewModel>()
            TaskDetailScreen(
                taskDetailViewModel = taskDetailViewModel,
                taskId = taskId,
                navBack = navController.navBack(),
                navEdit = { navController.navigate("$EDIT_TASK_START$taskId") }
            )
        }
        composable(
            route = AppDestination.EDIT_TASK,
            arguments = listOf(navArgument(TASK_ID_ARGUMENT) { type = NavType.StringType })
        ) { entry ->
            val taskId = entry.arguments?.getString(TASK_ID_ARGUMENT)
            if (taskId.isNullOrEmpty()) {
                throw IllegalArgumentException("EditTask arguments error")
            }
            val editTaskViewModel = hiltViewModel<EditTaskViewModel>()
            EditTaskScreen(
                editTaskViewModel = editTaskViewModel,
                taskId = taskId,
                navBack = navController.navBack()
            )
        }
        composable(AppDestination.NEW_TASK) {
            val editTaskViewModel = hiltViewModel<EditTaskViewModel>()
            EditTaskScreen(
                editTaskViewModel = editTaskViewModel,
                taskId = "",
                navBack = navController.navBack()
            )
        }

    }
}