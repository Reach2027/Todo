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

package com.reach.uilayer.ui.edittask

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.reach.uilayer.AniLoading
import com.reach.uilayer.AppAnimatedVisibility
import com.reach.uilayer.InputText
import com.reach.uilayer.theme.TodoTheme
import com.reach.uilayer.ui.SharedViewModel

/**
 * 2022/1/31  Reach
 */

@Composable
fun EditTaskScreen(
    sharedViewModel: SharedViewModel,
    editTaskViewModel: EditTaskViewModel = hiltViewModel(),
    taskId: String,
    navBack: () -> Unit
) {
    editTaskViewModel.setTaskId(taskId)
    val uiState: EditTaskUiState by editTaskViewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        AniLoading()
        return
    }

    val fabAction: () -> Unit

    if (uiState.newTask) {
        fabAction = {
            if (uiState.content.text.isEmpty()) {
                sharedViewModel.showSnackbar("To Do cannot be empty")
            } else {
                navBack()
                editTaskViewModel.save()
                sharedViewModel.showSnackbar("To Do added")
            }
        }
    } else {
        fabAction = {
            if (uiState.content.text.isEmpty()) {
                sharedViewModel.showSnackbar("To Do cannot be empty")
            } else {
                navBack()
                editTaskViewModel.update()
                sharedViewModel.showSnackbar("To Do saved")
            }
        }
    }

    EditTaskBody(
        value = uiState.content,
        onValueChange = { value -> editTaskViewModel.updateContent(value) },
        fabAction = fabAction
    )
}

@Composable
private fun EditTaskBody(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    fabAction: () -> Unit
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
            InputTask(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
            )
        }

        FloatingActionButton(
            onClick = fabAction,
            modifier = Modifier
                .padding(end = 32.dp, bottom = 32.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Done,
                contentDescription = "Save"
            )
        }
    }
}

@Composable
private fun InputTask(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier
) {

    InputText(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth(),
        label = {
            Text(
                text = "To Do Content"
            )
        },
        trailingIcon = {
            AppAnimatedVisibility(value.text.isNotBlank()) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Clear text",
                    modifier = Modifier
                        .padding(end = 8.dp, top = 14.dp)
                        .clickable { onValueChange(TextFieldValue("")) }
                )
            }
        },
        maxLines = 3
    )
}

@Preview
@Composable
private fun EditTaskBodyPreview() {
    TodoTheme {
        EditTaskBody(TextFieldValue("content\n22\n1234"), { }, { })
    }
}

@Preview
@Composable
private fun InputTextPreview() {
    TodoTheme {
        InputTask(value = TextFieldValue("test content"), onValueChange = { })
    }
}
