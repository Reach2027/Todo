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

package com.reach.uilayer.ui.you

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NavigateBefore
import androidx.compose.material.icons.rounded.NavigateNext
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.reach.uilayer.AniLoading

/**
 * 2022/2/4  Reach
 */

@Composable
fun YouScreen(youViewModel: YouViewModel = hiltViewModel()) {

    val uiState: YouUiState by youViewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        AniLoading()
        return
    }
    YouBody(uiState) { youViewModel.changeImage(it) }
}

@Composable
fun YouBody(
    uiState: YouUiState,
    changeImage: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = uiState.imageDate,
                modifier = Modifier.weight(weight = 1f, fill = true),
                style = MaterialTheme.typography.body1
            )
            Icon(
                imageVector = Icons.Rounded.NavigateBefore,
                contentDescription = "before",
                modifier = Modifier
                    .size(48.dp, 48.dp)
                    .clickable(enabled = uiState.beforeEnabled) { changeImage(false) }
                    .alpha(if (uiState.beforeEnabled) 0.7f else 0.05f)
            )
            Icon(
                imageVector = Icons.Rounded.NavigateNext,
                contentDescription = "next",
                modifier = Modifier
                    .padding(start = 48.dp)
                    .size(48.dp, 48.dp)
                    .clickable(enabled = uiState.nextEnabled) { changeImage(true) }
                    .alpha(if (uiState.nextEnabled) 0.7f else 0.05f)
            )
        }
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = uiState.imageUrl)
                    .crossfade(500)
                    .transformations(RoundedCornersTransformation(32f))
                    .size(1920, 1080)
                    .build()
            ),
            contentDescription = "Bing image of the day",
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = 16.dp)
        )
        Text(
            text = uiState.imageTitle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            style = MaterialTheme.typography.body1
        )
        Text(
            text = uiState.imageCopyright,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            style = MaterialTheme.typography.body2
        )
    }
}
