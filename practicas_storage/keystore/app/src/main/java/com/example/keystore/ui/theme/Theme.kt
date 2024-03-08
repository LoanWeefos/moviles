package com.example.keystore.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Purple500,
    secondary = Teal200
)

private val LightColorScheme = lightColorScheme(
    primary = Purple500,
    secondary = Teal200
)


@Composable
fun KeystoreTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}