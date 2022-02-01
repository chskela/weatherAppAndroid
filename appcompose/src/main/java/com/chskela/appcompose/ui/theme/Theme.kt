package com.chskela.appcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = Gray,
    onPrimary = Color.White,
)

private val LightColorPalette = lightColorScheme(
    primary = Gray,
    onPrimary = Color.White,
    primaryContainer = GrayLight,
    onPrimaryContainer = Gray

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun WeatherAppAndroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
//        colorScheme = colorScheme,
        colorScheme = LightColorPalette,
        typography = Typography,
//        shapes = Shapes,
        content = content
    )
}