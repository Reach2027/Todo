package com.reach.todo.ui.statistics

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reach.todo.data.entity.Task
import com.reach.todo.repository.TaskRepository
import com.reach.todo.ui.theme.Blue
import com.reach.todo.ui.theme.Green
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

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