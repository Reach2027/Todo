package com.reach.todo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * 2022/2/1  Reach
 */

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
fun NoTask() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "You have no To Do!\n\nAdd a To Do?",
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
