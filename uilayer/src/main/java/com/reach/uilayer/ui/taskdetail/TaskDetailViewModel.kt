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

package com.reach.uilayer.ui.taskdetail

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.reach.base.UiStateViewModel
import com.reach.datalayer.local.entity.Task
import com.reach.datalayer.repository.TaskRepository
import com.reach.domainlayer.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

/**
 * 2022/2/1  Reach
 */

@Immutable
data class TaskDetailUiState(
    val isLoading: Boolean = true,
    val task: Task? = null
)

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val taskRepository: TaskRepository
) : UiStateViewModel<TaskDetailUiState>(TaskDetailUiState()) {

    private val taskId = MutableStateFlow("")

    private val currentTask: Flow<Task?> = taskId.flatMapLatest { taskRepository.getTask(it) }

    init {
        viewModelScope.launch {
            currentTask.collectLatest { task ->
                updateUiState { copy(isLoading = false, task = task) }
            }
        }
    }

    fun setTaskId(uid: String) {
        if (taskId.value != uid) {
            taskId.value = uid
            updateUiState { copy(isLoading = true) }
        }
    }

    fun update(task: Task) {
        updateTaskUseCase(task)
    }

    fun delete(task: Task) {
        viewModelScope.launch {
            taskRepository.delete(task)
        }
    }
}
