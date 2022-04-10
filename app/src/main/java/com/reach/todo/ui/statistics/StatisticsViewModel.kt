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

package com.reach.todo.ui.statistics

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reach.datalayer.local.entity.Task
import com.reach.datalayer.repository.TaskRepository
import com.reach.todo.ui.theme.Blue
import com.reach.todo.ui.theme.Green
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * 2022/2/2  Reach
 */

@Immutable
data class StatisticsUiState(
    val isLoading: Boolean = true,
    val noTask: Boolean = false,
    val proportions: List<Float> = emptyList(),
    val colors: List<Color> = emptyList(),
    val activeSize: Int = 0,
    val finishedSize: Int = 0
)

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StatisticsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            taskRepository.getTasks().collectLatest { updateState(it) }
        }
    }

    private fun updateState(tasks: List<Task>) {
        if (tasks.isEmpty()) {
            _uiState.update { it.copy(isLoading = false, noTask = true) }
            return
        }
        val all = tasks.size
        var finishedSize = 0
        for (task in tasks) {
            if (task.finished) {
                finishedSize++
            }
        }
        val finishedPercent = finishedSize.toFloat() / all.toFloat()
        _uiState.update {
            it.copy(
                isLoading = false,
                noTask = false,
                proportions = listOf(finishedPercent, 1f - finishedPercent),
                colors = listOf(Blue, Green),
                activeSize = all - finishedSize,
                finishedSize = finishedSize
            )
        }
    }
}
