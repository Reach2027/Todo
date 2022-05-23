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

package com.reach.uilayer.ui.statistics

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.reach.commonandroid.UiStateViewModel
import com.reach.datalayer.database.entities.Task
import com.reach.datalayer.repository.TaskRepository
import com.reach.uilayer.theme.Blue
import com.reach.uilayer.theme.Green
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
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
) : UiStateViewModel<StatisticsUiState>(StatisticsUiState()) {

    init {
        viewModelScope.launch {
            taskRepository.getTasks().collectLatest { updateState(it) }
        }
    }

    private fun updateState(tasks: List<Task>) {
        if (tasks.isEmpty()) {
            updateUiState { copy(isLoading = false, noTask = true) }
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
        updateUiState {
            copy(
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
