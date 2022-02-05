@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")

package com.reach.todo.ui.components

import androidx.compose.animation.*
import androidx.compose.runtime.Composable

/**
 * 2022/2/1  Reach
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppAnimatedVisibility(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandVertically(),
        exit = shrinkVertically() + fadeOut(),
        content = content
    )
}