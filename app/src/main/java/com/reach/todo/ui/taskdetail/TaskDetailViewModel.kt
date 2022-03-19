package com.reach.todo.ui.taskdetail

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reach.todo.data.entity.Task
import com.reach.todo.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

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
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TaskDetailUiState())
    val uiState = _uiState.asStateFlow()

    private val taskId = MutableStateFlow("")

    val task: Flow<Task> = taskId.flatMapLatest { taskRepository.getTask(it) }

    init {
        viewModelScope.launch {
            task.collectLatest { task ->
                _uiState.update { it.copy(isLoading = false, task = task) }
            }
        }

    }

    fun setTaskId(uid: String) {
        if (taskId.value != uid) {
            taskId.value = uid
            _uiState.update { it.copy(isLoading = true) }
        }
    }

    fun update(task: Task) {
        viewModelScope.launch {
            taskRepository.update(task)
        }
    }

    fun delete(task: Task) {
        viewModelScope.launch {
            taskRepository.delete(task)
        }
    }

}