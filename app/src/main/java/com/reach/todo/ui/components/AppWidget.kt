package com.reach.todo.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.reach.todo.FILTER_ACTIVE
import com.reach.todo.FILTER_ALL
import com.reach.todo.FILTER_COMPLETED

/**
 * 2022/2/1  Reach
 */

@Composable
fun AppSelectedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String
) {
    Button(
        onClick = onClick,
        modifier = modifier.padding(start = 8.dp),
        enabled = enabled,
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary.copy(0.7f),
            disabledBackgroundColor = MaterialTheme.colors.secondary,
            disabledContentColor = MaterialTheme.colors.onSecondary
        ),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun InputText(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = false,
    maxLines: Int = 10,
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        label = label,
        trailingIcon = trailingIcon,
        singleLine = singleLine,
        maxLines = maxLines,
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = MaterialTheme.colors.primaryVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            backgroundColor = MaterialTheme.colors.primary,
            focusedLabelColor = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium),
            unfocusedLabelColor = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
        )
    )
}

@Composable
fun NoTask(taskFilter: Int = FILTER_ALL) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val text = when (taskFilter) {
            FILTER_ACTIVE -> {
                "You have no active To Do!"
            }
            FILTER_COMPLETED -> {
                "You have no completed To Do!"
            }
            else -> {
                "You have no To Do!\n\nAdd a To Do?"
            }
        }
        Text(
            text = text,
            style = MaterialTheme.typography.h6
        )
    }
}

@Composable
fun AppLoadingBar() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(100.dp),
            color = MaterialTheme.colors.secondary,
            strokeWidth = 8.dp
        )

    }
}
