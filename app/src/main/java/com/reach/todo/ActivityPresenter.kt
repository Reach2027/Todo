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

package com.reach.todo

import com.reach.todo.navigation.AppDestination
import com.reach.todo.navigation.BottomSections
import com.reach.todo.navigation.TITLE_EDIT_TASK
import com.reach.todo.navigation.TITLE_NEW_TASK
import com.reach.todo.navigation.TITLE_STATISTICS
import com.reach.todo.navigation.TITLE_TASKS
import com.reach.todo.navigation.TITLE_TASK_DETAIL
import com.reach.todo.navigation.TITLE_YOU
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * 2022/6/1  Reach
 */
data class ActivityUiState(
    val currentRoute: String = AppDestination.STATISTICS,
    val title: String = TITLE_STATISTICS,
    val showBottomBar: Boolean = true,
    val showBackIcon: Boolean = false,
    val bottomSections: List<BottomSections> = listOf(
        BottomSections.TASKS,
        BottomSections.STATISTICS,
        BottomSections.YOU
    )
)

@Singleton
class ActivityPresenter @Inject constructor() {

    private val _uiState = MutableStateFlow(ActivityUiState())

    val uiState = _uiState.asStateFlow()

    private val bottomRoutes = listOf(
        AppDestination.TASKS,
        AppDestination.STATISTICS,
        AppDestination.YOU
    )

    fun setCurrentRoute(currentRoute: String?) {
        if (currentRoute == null || currentRoute == uiState.value.currentRoute) {
            return
        }
        val showBottomBar = currentRoute in bottomRoutes
        _uiState.update {
            it.copy(
                currentRoute = currentRoute,
                title = obtainCurrentTitle(currentRoute),
                showBottomBar = showBottomBar,
                showBackIcon = !showBottomBar
            )
        }
    }

    private fun obtainCurrentTitle(route: String) = when (route) {
        AppDestination.TASKS -> TITLE_TASKS
        AppDestination.STATISTICS -> TITLE_STATISTICS
        AppDestination.YOU -> TITLE_YOU
        AppDestination.TASK_DETAIL -> TITLE_TASK_DETAIL
        AppDestination.EDIT_TASK -> TITLE_EDIT_TASK
        AppDestination.NEW_TASK -> TITLE_NEW_TASK
        else -> TITLE_STATISTICS
    }
}
