@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")

package com.reach.todo.ui.edittask

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.reach.todo.data.entity.Task
import com.reach.todo.ui.components.AppAnimatedVisibility
import com.reach.todo.ui.components.AppLoadingBar
import com.reach.todo.ui.components.InputText
import com.reach.todo.ui.theme.TodoTheme

/**
 * 2022/1/31  Reach
 */

@Composable
fun EditTaskScreen(
    editTaskViewModel: EditTaskViewModel,
    taskId: String,
    navBack: () -> Unit
) {
    val uiState: EditTaskUiState by editTaskViewModel.uiState.collectAsState()
    editTaskViewModel.setTaskId(taskId)

    if (uiState.isLoading) {
        AppLoadingBar()
        return
    }

    val content: MutableState<String>

    val fabAction: () -> Unit

    if (uiState.newTask) {
        content = rememberSaveable { mutableStateOf("") }
        fabAction = {
            if (content.value.isEmpty()) {
                // TODO:
            } else {
                navBack()
                editTaskViewModel.save(Task(content = content.value))
            }
        }
    } else {
        content = rememberSaveable { mutableStateOf(uiState.task!!.content) }
        fabAction = {
            if (content.value.isEmpty()) {
                // TODO:
            } else {
                navBack()
                editTaskViewModel.update(content = content.value)
            }
        }
    }

    EditTaskBody(
        text = content.value,
        onTextChange = { text -> content.value = text },
        fabAction = fabAction
    )

}

@Composable
private fun EditTaskBody(
    text: String,
    onTextChange: (String) -> Unit,
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
                text = text,
                onTextChange = onTextChange,
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun InputTask(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    InputText(
        text = text,
        onTextChange = onTextChange,
        modifier = modifier
            .fillMaxWidth(),
        label = {
            Text(
                text = "To Do Content"
            )
        },
        trailingIcon = {
            AppAnimatedVisibility(text.isNotBlank()) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Clear text",
                    modifier = Modifier
                        .padding(end = 8.dp, top = 14.dp)
                        .clickable { onTextChange("") }
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
        EditTaskBody("content\n22\n1234", { }, { })
    }
}

@Preview
@Composable
private fun InputTextPreview() {
    TodoTheme {
        InputTask(text = "test content", onTextChange = { })
    }
}
