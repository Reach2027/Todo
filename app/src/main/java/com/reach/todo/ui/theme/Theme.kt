package com.reach.todo.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColors = darkColors(
    primary = DarkBlack,
    primaryVariant = DarkWhite,
    onPrimary = DarkWhite,

    secondary = MainBlue,
    secondaryVariant = DarkWhite,
    onSecondary = DarkWhite,

    background = DarkBackground,
    onBackground = DarkWhite,

    surface = DarkSurface,
    onSurface = DarkWhite,

    error = Red200,
    onError = DarkWhite
)

@SuppressLint("ConflictingOnColor")
private val LightColors = lightColors(
    primary = LightWhite,
    primaryVariant = LightBlack,
    onPrimary = LightBlack,

    secondary = MainBlue,
    secondaryVariant = LightWhite,
    onSecondary = LightWhite,

    background = LightBackground,
    onBackground = LightBlack,

    surface = LightSurface,
    onSurface = LightBlack,

    error = Red800,
    onError = LightWhite
)

@Composable
fun TodoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}