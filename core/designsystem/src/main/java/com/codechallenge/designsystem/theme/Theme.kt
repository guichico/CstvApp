package com.codechallenge.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = White,
    secondary = White,
    tertiary = White,

    background = BackgroundColor,
    surface = BackgroundColor,
    onPrimary = BackgroundColor,
    onSecondary = White,
    onSurface = White,
    onBackground = White,
)

private val LightColorScheme = lightColorScheme(
    primary = White,
    secondary = White,
    tertiary = White,

    background = BackgroundColor,
    surface = BackgroundColor,
    onPrimary = BackgroundColor,
    onSecondary = White,
    onSurface = White,
    onBackground = White,

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

object CstvAppTheme {
    val spacing: Spacings
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacing.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

@Composable
fun CstvAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    // Disabled dynamicColor for this project
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme
    ) {
        CompositionLocalProvider(
            LocalTypography provides CstvAppTheme.typography,
            LocalSpacing provides CstvAppTheme.spacing
        ) {
            content()
        }
    }
}