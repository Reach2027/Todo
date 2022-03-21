@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")

package com.reach.todo.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

private const val LOADING = 800

private const val SPIN = 180f

private const val OFFSET = 150f

@Preview(widthDp = 380)
@Composable
fun AniLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        var moved by remember { mutableStateOf(false) }

        LaunchedEffect(true) {
            while (true) {
                moved = !moved
                delay(1400L)
            }
        }
        for (i in 0 until 3) {
            AniRect(
                idx = i,
                moved = moved
            )
        }

    }
}

@Composable
private fun AniRect(
    idx: Int,
    moved: Boolean
) {
    val offset = remember { Animatable(-OFFSET) }

    val spin = remember { Animatable(-SPIN) }

    LaunchedEffect(moved) {
        delay((200 * idx).toLong())
        launch {
            offset.animateTo(
                targetValue = if (moved) OFFSET else -OFFSET,
                animationSpec = tween(
                    durationMillis = LOADING
                )
            )
        }
        launch {
            spin.animateTo(
                targetValue = if (moved) SPIN else -SPIN,
                animationSpec = tween(
                    durationMillis = LOADING
                )
            )
        }
    }
    Rect(
        color = MaterialTheme.colors.onPrimary,
        offset = offset.value,
        spin = spin.value
    )
}

@Composable
private fun Rect(
    color: Color,
    offset: Float,
    spin: Float
) {
    Canvas(
        Modifier
            .size(60.dp)
            .offset(x = offset.dp)
            .graphicsLayer { rotationZ = spin }
    ) {
        drawRoundRect(
            color = color,
            cornerRadius = CornerRadius(56f),
            style = Stroke(8f)
        )
    }
}

private const val DividerLengthInDegrees = 2f

/**
 * copy compose sample rally
 */
@Composable
fun AnimatedCircle(
    proportions: List<Float>,
    colors: List<Color>,
    modifier: Modifier = Modifier
) {
    val currentState = remember {
        MutableTransitionState(AnimatedCircleProgress.START)
            .apply { targetState = AnimatedCircleProgress.END }
    }
    val stroke = with(LocalDensity.current) { Stroke(8.dp.toPx()) }
    val transition = updateTransition(currentState, label = "")
    val angleOffset by transition.animateFloat(
        transitionSpec = {
            tween(
                delayMillis = 100,
                durationMillis = 900,
                easing = LinearOutSlowInEasing
            )
        },
        label = ""
    ) { progress ->
        if (progress == AnimatedCircleProgress.START) {
            0f
        } else {
            360f
        }
    }
    val shift by transition.animateFloat(
        transitionSpec = {
            tween(
                delayMillis = 100,
                durationMillis = 900,
                easing = CubicBezierEasing(0f, 0.75f, 0.35f, 0.85f)
            )
        },
        label = ""
    ) { progress ->
        if (progress == AnimatedCircleProgress.START) {
            -180f
        } else {
            0f
        }
    }

    Canvas(modifier) {
        val innerRadius = (size.minDimension - stroke.width) / 2
        val halfSize = size / 2.0f
        val topLeft = Offset(
            halfSize.width - innerRadius,
            halfSize.height - innerRadius
        )
        val size = Size(innerRadius * 2, innerRadius * 2)
        var startAngle = shift - 90f
        proportions.forEachIndexed { index, proportion ->
            val sweep = proportion * angleOffset
            drawArc(
                color = colors[index],
                startAngle = startAngle + DividerLengthInDegrees / 2,
                sweepAngle = sweep - DividerLengthInDegrees,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = stroke
            )
            startAngle += sweep
        }
    }
}

private enum class AnimatedCircleProgress { START, END }
