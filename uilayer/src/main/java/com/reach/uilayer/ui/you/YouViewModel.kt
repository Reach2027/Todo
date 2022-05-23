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
import com.reach.commonandroid.UiStateViewModel
import com.reach.datalayer.BING_BASE_URL
import com.reach.datalayer.remote.bing.BingRemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.launch

/**
 * 2022/2/5  Reach
 */

@Immutable
data class YouUiState(
    val isLoading: Boolean = true,
    val imageDate: String = "",
    val imageUrl: String = "",
    val title: String = "",
    val copyright: String = ""
)

@HiltViewModel
class YouViewModel @Inject constructor(
    private val bingDataSource: BingRemoteDataSource
) : UiStateViewModel<YouUiState>(YouUiState()) {

    private var imageIndex = 0

    init {
        viewModelScope.launch {
            bingDataSource.getImageInfo().collect { bingResult ->
                val image = bingResult.images[imageIndex]
                updateUiState {
                    copy(
                        isLoading = false,
                        imageDate = processDate(image.enddate),
                        imageUrl = BING_BASE_URL + image.url,
                        title = image.title,
                        copyright = image.copyright
                    )
                }
            }
        }
    }

    private fun processDate(date: String): String {
        val calendar = Calendar.getInstance()
        calendar.set(
            date.substring(0, 4).toInt(),
            date.substring(4, 6).toInt() - 1,
            date.substring(6, 8).toInt()
        )
        val format = SimpleDateFormat("M . d  -  yyyy", Locale.getDefault())
        return format.format(calendar.time)
    }
}
