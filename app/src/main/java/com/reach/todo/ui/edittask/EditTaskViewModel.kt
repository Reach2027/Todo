package com.reach.todo.ui.edittask

import androidx.compose.runtime.Immutable
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
import java.util.*
import javax.inject.Inject

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
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditTaskUiState())
    val uiState = _uiState.asStateFlow()

    private val taskId = MutableStateFlow("")

    init {
        viewModelScope.launch {
            taskId.collectLatest { taskId ->
                if (taskId.isEmpty()) {
                    return@collectLatest
                }
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        task = taskRepository.getTask(taskId)
                    )
                }
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
        viewModelScope.launch {
            val current: Task = _uiState.value.task!!
            current.content = content
            current.editTime = Calendar.getInstance()
            taskRepository.update(current)
        }
    }

}