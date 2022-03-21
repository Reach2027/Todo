package com.reach.todo.ui.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.reach.todo.ui.components.AniLoading
import com.reach.todo.ui.components.AnimatedCircle
import com.reach.todo.ui.components.NoTask

/**
 * 2022/2/2  Reach
 */

@Composable
fun StatisticsScreen(
    statisticsViewModel: StatisticsViewModel,
) {
    val uiState by statisticsViewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        AniLoading()
        return
    }
    if (uiState.noTask) {
        NoTask()
        return
    }

    StatisticsBody(uiState)
}

@Composable
private fun StatisticsBody(uiState: StatisticsUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AnimatedCircle(
            uiState.proportions,
            uiState.colors,
            Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = "Completed To Do : ${uiState.finishedSize}",
            modifier = Modifier.padding(16.dp),
            color = uiState.colors[0],
            style = MaterialTheme.typography.body1
        )
        Text(
            text = "Active To Do : ${uiState.activeSize}",
            modifier = Modifier.padding(16.dp),
            color = uiState.colors[1],
            style = MaterialTheme.typography.body1
        )

    }
}