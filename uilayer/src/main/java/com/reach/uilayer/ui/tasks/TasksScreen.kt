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

package com.reach.uilayer.ui.tasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.reach.datalayer.database.entities.Task
import com.reach.uilayer.AniLoading
import com.reach.uilayer.AppSelectedButton
import com.reach.uilayer.FILTER_ACTIVE
import com.reach.uilayer.FILTER_ALL
import com.reach.uilayer.FILTER_COMPLETED
import com.reach.uilayer.NoTask
import com.reach.uilayer.theme.TodoTheme
import com.reach.uilayer.utils.calendarToString

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
        AniLoading()
        return
    }

    val listState: LazyListState = rememberLazyListState()
    LaunchedEffect(key1 = uiState.tasks) {
        listState.scrollToItem(0)
    }

    TasksBody(
        uiState = uiState,
        listState = listState,
        navTaskDetail = navTaskDetail,
        navAddTask = navAddTask,
        update = { tasksViewModel.update(it) },
        filter = { tasksViewModel.setTaskFilter(it) }
    )
}

@Composable
private fun TasksBody(
    uiState: TasksUiState,
    listState: LazyListState,
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
                    .padding(vertical = 8.dp)
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

            TaskList(uiState.tasks, listState, update, navTaskDetail)
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
    listState: LazyListState,
    update: (Task) -> Unit,
    navTaskDetail: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState
    ) {
        items(
            items = tasks,
            key = { item: Task -> item.uid }
        ) { task ->
            TaskItem(task, update, navTaskDetail)
        }
    }
}

@Composable
private fun TaskItem(
    task: Task,
    update: (Task) -> Unit,
    navTaskDetail: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
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
                    if (task.finished) TextDecoration.LineThrough else TextDecoration.None,
                    maxLines = 1,
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.padding(vertical = 12.dp))
                Text(
                    text = calendarToString(task.createTime),
                    textDecoration =
                    if (task.finished) TextDecoration.LineThrough else TextDecoration.None,
                    maxLines = 1,
                    style = MaterialTheme.typography.body2
                )
            }

            Checkbox(
                checked = task.finished,
                onCheckedChange = {
                    val current = task.copy(finished = it)
                    update(current)
                },
            )
        }
    }
}

@Preview
@Composable
private fun TaskItemPreview() {
    TodoTheme {
        TaskItem(task = Task(content = "test content"), { }, { })
    }
}
