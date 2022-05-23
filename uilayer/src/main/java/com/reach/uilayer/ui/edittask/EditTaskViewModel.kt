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

package com.reach.uilayer.ui.edittask

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.reach.commonandroid.UiStateViewModel
import com.reach.datalayer.database.entities.Task
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
 * 2022/1/31  Reach
 */

@Immutable
data class EditTaskUiState(
    val isLoading: Boolean = true,
    val newTask: Boolean = true,
    val content: TextFieldValue = TextFieldValue("")
)

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val taskRepository: TaskRepository
) : UiStateViewModel<EditTaskUiState>(EditTaskUiState()) {

    private val taskId = MutableStateFlow("")

    private val task: Flow<Task?> = taskId.flatMapLatest { taskRepository.getTask(it) }

    private lateinit var currentTask: Task

    init {
        viewModelScope.launch {
            task.collectLatest { task ->
                if (task == null) {
                    return@collectLatest
                }
                currentTask = task
                updateUiState {
                    copy(
                        isLoading = false,
                        content = TextFieldValue(task.content)
                    )
                }
            }
        }
    }

    fun setTaskId(uid: String) {
        if (uid.isEmpty()) {
            updateUiState { copy(isLoading = false, newTask = true) }
            return
        }
        if (taskId.value != uid) {
            taskId.value = uid
            updateUiState { copy(isLoading = true, newTask = false) }
        }
    }

    fun updateContent(value: TextFieldValue) {
        updateUiState { copy(content = value) }
    }

    fun save() {
        viewModelScope.launch {
            val task = Task(content = uiStateValue().content.text)
            taskRepository.add(task)
        }
    }

    fun update() {
        currentTask.content = uiStateValue().content.text
        updateTaskUseCase(currentTask)
    }
}
