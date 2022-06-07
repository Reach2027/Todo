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

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.reach.uilayer.enter
import com.reach.uilayer.exit
import com.reach.uilayer.ui.SharedViewModel
import com.reach.uilayer.ui.edittask.EditTaskScreen
import com.reach.uilayer.ui.statistics.StatisticsScreen
import com.reach.uilayer.ui.taskdetail.TaskDetailScreen
import com.reach.uilayer.ui.tasks.TasksScreen
import com.reach.uilayer.ui.you.YouScreen

/**
 * 2022/1/30  Reach
 */
@ExperimentalAnimationApi
@Composable
fun AppNavGraph(
    sharedViewModel: SharedViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = AppDestination.STATISTICS,
        modifier = modifier,
        enterTransition = { enter() },
        exitTransition = { exit() }
    ) {
        taskGraph(navController, sharedViewModel)

        composable(AppDestination.STATISTICS) {
            StatisticsScreen()
        }

        composable(AppDestination.YOU) {
            YouScreen()
        }
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.taskGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    navigation(startDestination = AppDestination.TASKS, route = "task") {
        composable(AppDestination.TASKS) {
            TasksScreen(
                navTaskDetail = { taskId ->
                    navController.navigate("$TASK_DETAIL_START$taskId")
                },
                navAddTask = { navController.navigate(AppDestination.NEW_TASK) }
            )
        }
        composable(
            route = AppDestination.TASK_DETAIL,
            arguments = listOf(navArgument(ARGUMENT_TASK_ID) { type = NavType.StringType })
        ) { entry ->
            val taskId = entry.arguments?.getString(ARGUMENT_TASK_ID)
            if (taskId.isNullOrEmpty()) {
                throw IllegalArgumentException("TaskDetail arguments error")
            }
            TaskDetailScreen(
                sharedViewModel = sharedViewModel,
                taskId = taskId,
                navBack = navController.navBack(),
                navEdit = { navController.navigate("$EDIT_TASK_START$taskId") }
            )
        }
        composable(
            route = AppDestination.EDIT_TASK,
            arguments = listOf(navArgument(ARGUMENT_TASK_ID) { type = NavType.StringType })
        ) { entry ->
            val taskId = entry.arguments?.getString(ARGUMENT_TASK_ID)
            if (taskId.isNullOrEmpty()) {
                throw IllegalArgumentException("EditTask arguments error")
            }
            EditTaskScreen(
                sharedViewModel = sharedViewModel,
                taskId = taskId,
                navBack = navController.navBack()
            )
        }
        composable(AppDestination.NEW_TASK) {
            EditTaskScreen(
                sharedViewModel = sharedViewModel,
                taskId = "",
                navBack = navController.navBack()
            )
        }
    }
}
