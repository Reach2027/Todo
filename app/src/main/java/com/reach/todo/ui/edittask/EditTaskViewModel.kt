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

package com.reach.todo.ui.edittask

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.reach.datalayer.local.entity.Task
import com.reach.datalayer.repository.TaskRepository
import com.reach.todo.UiStateViewModel
import com.reach.todo.usecase.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * 2022/1/31  Reach
 */

@Immutable
data class EditTaskUiState(
    val isLoading: Boolean = true,
    val newTask: Boolean = true,
    val task: Task? = null
)

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val taskRepository: TaskRepository
) : UiStateViewModel<EditTaskUiState>(EditTaskUiState()) {

    private val taskId = MutableStateFlow("")

    private val task: Flow<Task> = taskId.flatMapLatest { taskRepository.getTask(it) }

    init {
        viewModelScope.launch {
            task.collectLatest { task ->
                _uiState.update { it.copy(isLoading = false, task = task) }
            }
        }
    }

    fun setTaskId(uid: String) {
        if (uid.isEmpty()) {
            _uiState.update { it.copy(isLoading = false, newTask = true) }
            return
        }
        if (taskId.value != uid) {
            taskId.value = uid
            _uiState.update { it.copy(isLoading = true, newTask = false) }
        }
    }

    fun save(task: Task) {
        viewModelScope.launch {
            taskRepository.add(task)
        }
    }

    fun update(content: String) {
        val current: Task = _uiState.value.task!!
        current.content = content
        updateTaskUseCase(current)
    }
}
