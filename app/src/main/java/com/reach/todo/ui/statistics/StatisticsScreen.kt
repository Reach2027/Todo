package com.reach.todo.ui.statistics

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.reach.todo.ui.components.NoTask

/**
 * 2022/2/2  Reach
 */

@Composable
fun StatisticsScreen(
    statisticsViewModel: StatisticsViewModel,
    navAddTask: () -> Unit,
) {
    StatisticsBody(navAddTask = navAddTask)
}

@Composable
private fun StatisticsBody(
    navAddTask: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        NoTask()

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