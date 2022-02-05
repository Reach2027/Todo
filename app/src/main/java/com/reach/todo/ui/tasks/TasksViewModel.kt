package com.reach.todo.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reach.todo.data.entity.Task
import com.reach.todo.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 2022/1/31  Reach
 */

data class TasksUiState(
    val isLoading: Boolean = true,
    val tasks: List<Task> = emptyList()
)

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TasksUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            taskRepository.getTasks().collectLatest { tasks ->
                _uiState.update { it.copy(isLoading = false, tasks = tasks) }
            }
        }
    }

    val tasks = taskRepository.getTasks()

    fun update(task: Task) {
        viewModelScope.launch {
            taskRepository.update(task)
        }
    }

}