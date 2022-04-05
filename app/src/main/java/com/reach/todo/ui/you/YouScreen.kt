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

package com.reach.todo.ui.you

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.reach.todo.ui.components.AniLoading

/**
 * 2022/2/4  Reach
 */

@Composable
fun YouScreen(youViewModel: YouViewModel) {

    val uiState: YouUiState by youViewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        AniLoading()
        return
    }
    YouBody(
        uiState.imageUrl,
        uiState.title,
        uiState.copyright
    )
}

@Composable
fun YouBody(
    picUrl: String,
    picTitle: String,
    picCopyright: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = picTitle,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.body1
        )
        Image(
            painter = rememberImagePainter(
                data = picUrl,
                builder = {
                    crossfade(700)
                    transformations(RoundedCornersTransformation(32f))
                    size(1920, 1080)
                }
            ),
            contentDescription = "Bing image of the day",
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = 16.dp),
        )

        Text(
            text = picCopyright,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            style = MaterialTheme.typography.body2
        )
    }
}
