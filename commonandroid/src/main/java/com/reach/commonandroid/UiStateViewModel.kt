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

package com.reach.commonandroid

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * 2022/4/10  Reach
 */
abstract class UiStateViewModel<UiState>(initialState: UiState) : ViewModel() {

    protected val _uiState = MutableStateFlow(initialState)

    val uiState = _uiState.asStateFlow()

    protected inline fun updateUiState(newState: UiState.() -> UiState) {
        _uiState.update(newState)
    }

    protected fun uiStateValue() = uiState.value
}
