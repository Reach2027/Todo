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

package com.reach.uilayer.ui.taskdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.reach.datalayer.local.entity.Task
import com.reach.uilayer.AniLoading
import com.reach.uilayer.theme.TodoTheme
import com.reach.uilayer.ui.activity.ActivityViewModel
import com.reach.uilayer.utils.calendarToString

/**
 * 2022/2/1  Reach
 */

@Composable
fun TaskDetailScreen(
    activityViewModel: ActivityViewModel,
    taskDetailViewModel: TaskDetailViewModel,
    taskId: String,
    navBack: () -> Unit,
    navEdit: () -> Unit
) {
    taskDetailViewModel.setTaskId(taskId)
    val uiState by taskDetailViewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        AniLoading()
        return
    }
    if (uiState.task == null) {
        return
    }
    TaskDetailBody(
        task = uiState.task!!,
        update = { taskDetailViewModel.update(it) },
        delete = {
            taskDetailViewModel.delete(it)
            navBack()
            activityViewModel.showMessage("To Do was deleted")
        },
        navEdit = navEdit
    )
}

@Composable
private fun TaskDetailBody(
    task: Task,
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
                    checked = task.finished,
                    onCheckedChange = {
                        val cur = task.copy(finished = it)
                        update(cur)
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
            { },
            { }
        )
    }
}

@Preview
@Composable
private fun TaskDetailBodyPreview() {
    TodoTheme {
        TaskDetailBody(
            Task(content = "test content"),
            { },
            { },
            { }
        )
    }
}
