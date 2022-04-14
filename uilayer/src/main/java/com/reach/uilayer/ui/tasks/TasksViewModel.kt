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

package com.reach.uilayer.ui.tasks

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.reach.datalayer.local.entity.Task
import com.reach.datalayer.repository.TaskRepository
import com.reach.domainlayer.UpdateTaskUseCase
import com.reach.uilayer.FILTER_ACTIVE
import com.reach.uilayer.FILTER_ALL
import com.reach.uilayer.UiStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * 2022/1/31  Reach
 */

@Immutable
data class TasksUiState(
    val isLoading: Boolean = true,
    val tasks: List<Task> = emptyList(),
    val taskFilter: Int = FILTER_ALL
)

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val taskRepository: TaskRepository
) : UiStateViewModel<TasksUiState>(TasksUiState()) {

    private var taskFilter: Int = FILTER_ALL

    private var tasks: List<Task> = emptyList()

    init {
        viewModelScope.launch {
            taskRepository.getTasks().collectLatest { tasks ->
                this@TasksViewModel.tasks = tasks
                updateUiState {
                    copy(isLoading = false, tasks = filterTask(), taskFilter = taskFilter)
                }
            }
        }
    }

    fun setTaskFilter(taskFilter: Int) {
        this.taskFilter = taskFilter
        viewModelScope.launch {
            updateUiState {
                copy(tasks = filterTask(), taskFilter = taskFilter)
            }
        }
    }

    fun update(task: Task) {
        updateTaskUseCase(task)
    }

    private fun filterTask(): List<Task> {
        if (taskFilter == FILTER_ALL) {
            return tasks
        }
        return tasks.filter {
            if (taskFilter == FILTER_ACTIVE) {
                !it.finished
            } else {
                it.finished
            }
        }
    }
}
