package com.opappdevs.mindfulmoment.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = MindfulColorTheme.md_theme_light_primary,
    onPrimary = MindfulColorTheme.md_theme_light_onPrimary,
    primaryContainer = MindfulColorTheme.md_theme_light_primaryContainer,
    onPrimaryContainer = MindfulColorTheme.md_theme_light_onPrimaryContainer,
    secondary = MindfulColorTheme.md_theme_light_secondary,
    onSecondary = MindfulColorTheme.md_theme_light_onSecondary,
    secondaryContainer = MindfulColorTheme.md_theme_light_secondaryContainer,
    onSecondaryContainer = MindfulColorTheme.md_theme_light_onSecondaryContainer,
    tertiary = MindfulColorTheme.md_theme_light_tertiary,
    onTertiary = MindfulColorTheme.md_theme_light_onTertiary,
    tertiaryContainer = MindfulColorTheme.md_theme_light_tertiaryContainer,
    onTertiaryContainer = MindfulColorTheme.md_theme_light_onTertiaryContainer,
    error = MindfulColorTheme.md_theme_light_error,
    errorContainer = MindfulColorTheme.md_theme_light_errorContainer,
    onError = MindfulColorTheme.md_theme_light_onError,
    onErrorContainer = MindfulColorTheme.md_theme_light_onErrorContainer,
    background = MindfulColorTheme.md_theme_light_background,
    onBackground = MindfulColorTheme.md_theme_light_onBackground,
    surface = MindfulColorTheme.md_theme_light_surface,
    onSurface = MindfulColorTheme.md_theme_light_onSurface,
    surfaceVariant = MindfulColorTheme.md_theme_light_surfaceVariant,
    onSurfaceVariant = MindfulColorTheme.md_theme_light_onSurfaceVariant,
    outline = MindfulColorTheme.md_theme_light_outline,
    inverseOnSurface = MindfulColorTheme.md_theme_light_inverseOnSurface,
    inverseSurface = MindfulColorTheme.md_theme_light_inverseSurface,
    inversePrimary = MindfulColorTheme.md_theme_light_inversePrimary,
    surfaceTint = MindfulColorTheme.md_theme_light_primary,
)

private val DarkColorScheme = darkColorScheme(
    primary = MindfulColorTheme.md_theme_dark_primary,
    onPrimary = MindfulColorTheme.md_theme_dark_onPrimary,
    primaryContainer = MindfulColorTheme.md_theme_dark_primaryContainer,
    onPrimaryContainer = MindfulColorTheme.md_theme_dark_onPrimaryContainer,
    secondary = MindfulColorTheme.md_theme_dark_secondary,
    onSecondary = MindfulColorTheme.md_theme_dark_onSecondary,
    secondaryContainer = MindfulColorTheme.md_theme_dark_secondaryContainer,
    onSecondaryContainer = MindfulColorTheme.md_theme_dark_onSecondaryContainer,
    tertiary = MindfulColorTheme.md_theme_dark_tertiary,
    onTertiary = MindfulColorTheme.md_theme_dark_onTertiary,
    tertiaryContainer = MindfulColorTheme.md_theme_dark_tertiaryContainer,
    onTertiaryContainer = MindfulColorTheme.md_theme_dark_onTertiaryContainer,
    error = MindfulColorTheme.md_theme_dark_error,
    errorContainer = MindfulColorTheme.md_theme_dark_errorContainer,
    onError = MindfulColorTheme.md_theme_dark_onError,
    onErrorContainer = MindfulColorTheme.md_theme_dark_onErrorContainer,
    background = MindfulColorTheme.md_theme_dark_background,
    onBackground = MindfulColorTheme.md_theme_dark_onBackground,
    surface = MindfulColorTheme.md_theme_dark_surface,
    onSurface = MindfulColorTheme.md_theme_dark_onSurface,
    surfaceVariant = MindfulColorTheme.md_theme_dark_surfaceVariant,
    onSurfaceVariant = MindfulColorTheme.md_theme_dark_onSurfaceVariant,
    outline = MindfulColorTheme.md_theme_dark_outline,
    inverseOnSurface = MindfulColorTheme.md_theme_dark_inverseOnSurface,
    inverseSurface = MindfulColorTheme.md_theme_dark_inverseSurface,
    inversePrimary = MindfulColorTheme.md_theme_dark_inversePrimary,
    surfaceTint = MindfulColorTheme.md_theme_dark_primary,
)

//@Composable
//fun AppTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    content: @Composable () -> Unit
//) {
//    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//        content = content
//    )
//}

@Composable
fun MindfulMomentTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}