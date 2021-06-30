package com.ang.acb.movienight.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = red700,
    primaryVariant = red900,
    onPrimary = Color.White,
    secondary = red700,
    secondaryVariant = red900,
    onSecondary = Color.White,
    error = red800
)

private val DarkThemeColors = darkColors(
    primary = red300,
    primaryVariant = red700,
    onPrimary = Color.Black,
    secondary = red300,
    onSecondary = Color.Black,
    error = red200
)

@Composable
fun MovieNightTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkThemeColors else LightThemeColors

    MaterialTheme(
        colors = colors,
        typography = moviesTypography,
        shapes = Shapes,
        content = content
    )
}