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

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.reach.base.UiStateViewModel
import com.reach.datalayer.BING_BASE_URL
import com.reach.datalayer.remote.bing.BingRemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

/**
 * 2022/2/5  Reach
 */

@Immutable
data class YouUiState(
    val isLoading: Boolean = true,
    val imageUrl: String = "",
    val copyright: String = "",
    val title: String = ""
)

@HiltViewModel
class YouViewModel @Inject constructor(
    private val bingDataSource: BingRemoteDataSource
) : UiStateViewModel<YouUiState>(YouUiState()) {

    init {
        viewModelScope.launch {
            bingDataSource.getImageInfo().collect { bingResult ->
                val image = bingResult.images[0]
                updateUiState {
                    copy(
                        isLoading = false,
                        imageUrl = BING_BASE_URL + image.url,
                        copyright = image.copyright,
                        title = image.title
                    )
                }
            }
        }
    }
}