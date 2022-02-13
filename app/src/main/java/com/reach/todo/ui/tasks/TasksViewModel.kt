package com.reach.todo.ui.tasks

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reach.todo.FILTER_ACTIVE
import com.reach.todo.FILTER_ALL
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

@Immutable
data class TasksUiState(
    val isLoading: Boolean = true,
    val tasks: List<Task> = emptyList(),
    val taskFilter: Int = FILTER_ALL
)

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TasksUiState())
    val uiState = _uiState.asStateFlow()

    private var taskFilter: Int = FILTER_ALL

    private var tasks: List<Task> = emptyList()

    init {
        viewModelScope.launch {
            taskRepository.getTasks().collectLatest { tasks ->
                this@TasksViewModel.tasks = tasks
                _uiState.update {
                    it.copy(isLoading = false, tasks = filterTask(), taskFilter = taskFilter)
                }
            }
        }
    }

    fun setTaskFilter(taskFilter: Int) {
        this.taskFilter = taskFilter
        viewModelScope.launch {
            _uiState.update {
                it.copy(tasks = filterTask(), taskFilter = taskFilter)
            }
        }
    }

    fun update(task: Task) {
        viewModelScope.launch {
            taskRepository.update(task)
        }
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