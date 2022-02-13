package com.reach.todo.ui

import androidx.lifecycle.ViewModel
import com.reach.todo.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * 2022/2/4  Reach
 */

data class MainUiState(
    val currentRoute: String = AppDestination.STATISTICS,
    val title: String = TITLE_STATISTICS,
    val showBottomBar: Boolean = true,
    val showBackIcon: Boolean = false,
)

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    private val _snackbarMessage = MutableStateFlow("")
    val snackbarMessage = _snackbarMessage.asStateFlow()

    val bottomSections = BottomSections.values()
    private val bottomRoutes = bottomSections.map { it.route }

    fun setCurrentRoute(currentRoute: String?) {
        if (currentRoute == null) {
            _uiState.value = MainUiState()
            return
        }
        val showBottomBar = currentRoute in bottomRoutes
        _uiState.update {
            it.copy(
                currentRoute = currentRoute,
                title = obtainCurrentTitle(currentRoute),
                showBottomBar = showBottomBar,
                showBackIcon = !showBottomBar
            )
        }
    }

    fun showMessage(content: String) {
        _snackbarMessage.value = content
    }

    private fun obtainCurrentTitle(route: String) = when (route) {
        AppDestination.TASKS -> TITLE_TASKS
        AppDestination.STATISTICS -> TITLE_STATISTICS
        AppDestination.YOU -> TITLE_YOU
        AppDestination.TASK_DETAIL -> TITLE_TASK_DETAIL
        AppDestination.EDIT_TASK -> TITLE_EDIT_TASK
        AppDestination.NEW_TASK -> TITLE_NEW_TASK
        else -> TITLE_STATISTICS
    }

}