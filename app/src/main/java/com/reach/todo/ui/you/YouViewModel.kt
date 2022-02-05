package com.reach.todo.ui.you

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reach.todo.data.remotesource.bing.BingDataSource
import com.reach.todo.data.remotesource.bing.BingServiceCreator.Companion.BASE_URL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 2022/2/5  Reach
 */

data class YouUiState(
    val isLoading: Boolean = true,
    val imageUrl: String = "",
    val copyright: String = "",
    val title: String = ""
)

@HiltViewModel
class YouViewModel @Inject constructor(
    private val bingDataSource: BingDataSource
) : ViewModel() {

    private val _uiState = MutableStateFlow(YouUiState())

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            bingDataSource.getImageUrl().collect { bingResult ->
                val image = bingResult.images[0]
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        imageUrl = BASE_URL + image.url,
                        copyright = image.copyright,
                        title = image.title
                    )
                }
            }

        }

    }

}