package com.reach.todo.ui.taskdetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.reach.todo.data.entity.Task
import com.reach.todo.ui.MainViewModel
import com.reach.todo.ui.components.AppLoadingBar
import com.reach.todo.ui.theme.TodoTheme
import com.reach.todo.utils.calendarToString

/**
 * 2022/2/1  Reach
 */

@Composable
fun TaskDetailScreen(
    mainViewModel: MainViewModel,
    taskDetailViewModel: TaskDetailViewModel,
    taskId: String,
    navBack: () -> Unit,
    navEdit: () -> Unit
) {
    val uiState by taskDetailViewModel.uiState.collectAsState()
    taskDetailViewModel.setTaskId(taskId)

    if (uiState.isLoading) {
        AppLoadingBar()
        return
    }
    if (uiState.task == null) {
        return
    }
    val (checked, setChecked) = remember {
        mutableStateOf(uiState.task!!.finished)
    }
    TaskDetailBody(
        task = uiState.task!!,
        checked = checked,
        onChecked = setChecked,
        update = { taskDetailViewModel.update(it) },
        delete = {
            taskDetailViewModel.delete(it)
            navBack()
            mainViewModel.showMessage("To Do was deleted")
        },
        navEdit = navEdit
    )

}

@Composable
private fun TaskDetailBody(
    task: Task,
    checked: Boolean,
    onChecked: (Boolean) -> Unit,
    update: (Task) -> Unit,
    delete: (Task) -> Unit,
    navEdit: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
        ) {
            TaskDetailItem(
                task = task,
                checked = checked,
                onChecked = onChecked,
                update = update,
                delete = delete,
            )

        }

        FloatingActionButton(
            onClick = navEdit,
            modifier = Modifier.padding(end = 32.dp, bottom = 32.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Edit to do"
            )
        }
    }
}

@Composable
private fun TaskDetailItem(
    task: Task,
    checked: Boolean,
    onChecked: (Boolean) -> Unit,
    update: (Task) -> Unit,
    delete: (Task) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 8.dp,
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = calendarToString(task.createTime),
                    modifier = Modifier
                        .weight(1f),
                    style = MaterialTheme.typography.body1,
                )
                IconButton(onClick = {
                    delete(task)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = task.content,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.body1,
                    maxLines = 3
                )
                Checkbox(
                    checked = checked,
                    onCheckedChange = {
                        onChecked(it)
                        task.finished = it
                        update(task)
                    },
                    modifier = Modifier.size(48.dp)
                )
            }
        }

    }
}

@Preview
@Composable
private fun TaskDetailItemPreview() {
    TodoTheme {
        TaskDetailItem(
            Task(content = "test content"),
            false,
            { },
            { },
            { })
    }
}

@Preview
@Composable
private fun TaskDetailBodyPreview() {
    TodoTheme {
        TaskDetailBody(
            Task(content = "test content"),
            false, { }, { },
            { }, { })
    }
}