package com.reach.todo.ui.tasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.reach.todo.FILTER_ACTIVE
import com.reach.todo.FILTER_ALL
import com.reach.todo.FILTER_COMPLETED
import com.reach.todo.data.entity.Task
import com.reach.todo.ui.components.AppLoadingBar
import com.reach.todo.ui.components.AppSelectedButton
import com.reach.todo.ui.components.NoTask
import com.reach.todo.ui.theme.TodoTheme
import com.reach.todo.utils.calendarToString

/**
 * 2022/1/31  Reach
 */

@Composable
fun TasksScreen(
    tasksViewModel: TasksViewModel,
    navTaskDetail: (String) -> Unit,
    navAddTask: () -> Unit,
) {
    val uiState by tasksViewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        AppLoadingBar()
        return
    }

    TasksBody(
        uiState = uiState,
        navTaskDetail = navTaskDetail,
        navAddTask = navAddTask,
        update = { tasksViewModel.update(it) },
        filter = { tasksViewModel.setTaskFilter(it) }
    )

}

@Composable
private fun TasksBody(
    uiState: TasksUiState,
    navTaskDetail: (String) -> Unit,
    navAddTask: () -> Unit,
    update: (Task) -> Unit,
    filter: (Int) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                AppSelectedButton(
                    onClick = { filter(FILTER_ALL) },
                    modifier = Modifier.padding(start = 8.dp),
                    enabled = uiState.taskFilter != FILTER_ALL,
                    text = "All"
                )
                AppSelectedButton(
                    onClick = { filter(FILTER_ACTIVE) },
                    enabled = uiState.taskFilter != FILTER_ACTIVE,
                    text = "Active"
                )
                AppSelectedButton(
                    onClick = { filter(FILTER_COMPLETED) },
                    enabled = uiState.taskFilter != FILTER_COMPLETED,
                    text = "Completed"
                )
            }

            TaskList(uiState.tasks, update, navTaskDetail)
        }

        if (uiState.tasks.isEmpty()) {
            NoTask(uiState.taskFilter)
        }

        FloatingActionButton(
            onClick = navAddTask,
            modifier = Modifier.padding(end = 32.dp, bottom = 32.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = "Add to do"
            )
        }
    }
}

@Composable
private fun TaskList(
    tasks: List<Task>,
    update: (Task) -> Unit,
    navTaskDetail: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(
            items = tasks,
            key = { item: Task -> item.uid }
        ) { task ->
            val (checked, setChecked) = remember {
                mutableStateOf(task.finished)
            }
            TaskItem(task, checked, setChecked, update, navTaskDetail)
        }
    }
}

@Composable
private fun TaskItem(
    task: Task,
    checked: Boolean,
    onChecked: (Boolean) -> Unit,
    update: (Task) -> Unit,
    navTaskDetail: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable { navTaskDetail(task.uid) },
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(all = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = task.content,
                    textDecoration =
                    if (checked) TextDecoration.LineThrough else TextDecoration.None,
                    maxLines = 1,
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.padding(vertical = 12.dp))
                Text(
                    text = calendarToString(task.createTime),
                    textDecoration =
                    if (checked) TextDecoration.LineThrough else TextDecoration.None,
                    maxLines = 1,
                    style = MaterialTheme.typography.body2
                )
            }

            Checkbox(
                checked = checked,
                onCheckedChange = {
                    onChecked(it)
                    task.finished = it
                    update(task)
                },
            )
        }
    }
}

@Preview
@Composable
private fun TaskItemPreview() {
    TodoTheme {
        TaskItem(task = Task(content = "test content"), true, { }, { }, { })
    }
}