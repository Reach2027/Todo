package com.reach.todo.ui.edittask

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.reach.todo.data.entity.Task
import com.reach.todo.ui.MainViewModel
import com.reach.todo.ui.components.AniLoading
import com.reach.todo.ui.components.AppAnimatedVisibility
import com.reach.todo.ui.components.InputText
import com.reach.todo.ui.theme.TodoTheme

/**
 * 2022/1/31  Reach
 */

@Composable
fun EditTaskScreen(
    mainViewModel: MainViewModel,
    editTaskViewModel: EditTaskViewModel,
    taskId: String,
    navBack: () -> Unit
) {
    val uiState: EditTaskUiState by editTaskViewModel.uiState.collectAsState()
    editTaskViewModel.setTaskId(taskId)

    if (uiState.isLoading) {
        AniLoading()
        return
    }

    val content: MutableState<String>

    val fabAction: () -> Unit

    if (uiState.newTask) {
        content = rememberSaveable { mutableStateOf("") }
        fabAction = {
            if (content.value.isEmpty()) {
                mainViewModel.showMessage("To Do cannot be empty")
            } else {
                navBack()
                editTaskViewModel.save(Task(content = content.value))
                mainViewModel.showMessage("To Do added")
            }
        }
    } else {
        content = rememberSaveable { mutableStateOf(uiState.task!!.content) }
        fabAction = {
            if (content.value.isEmpty()) {
                mainViewModel.showMessage("To Do cannot be empty")
            } else {
                navBack()
                editTaskViewModel.update(content = content.value)
                mainViewModel.showMessage("To Do saved")
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
