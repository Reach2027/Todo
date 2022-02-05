package com.reach.todo.ui.statistics

import androidx.lifecycle.ViewModel
import com.reach.todo.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * 2022/2/2  Reach
 */
@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val tasks = taskRepository.getTasks()

}