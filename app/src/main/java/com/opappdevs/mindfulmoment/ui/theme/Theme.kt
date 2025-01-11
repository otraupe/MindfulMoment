package com.opappdevs.mindfulmoment.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

//private val LightColorScheme = lightColorScheme(
//    primary = MindfulColorTheme.md_theme_light_primary,
//    onPrimary = MindfulColorTheme.md_theme_light_onPrimary,
//    primaryContainer = MindfulColorTheme.md_theme_light_primaryContainer,
//    onPrimaryContainer = MindfulColorTheme.md_theme_light_onPrimaryContainer,
//    secondary = MindfulColorTheme.md_theme_light_secondary,
//    onSecondary = MindfulColorTheme.md_theme_light_onSecondary,
//    secondaryContainer = MindfulColorTheme.md_theme_light_secondaryContainer,
//    onSecondaryContainer = MindfulColorTheme.md_theme_light_onSecondaryContainer,
//    tertiary = MindfulColorTheme.md_theme_light_tertiary,
//    onTertiary = MindfulColorTheme.md_theme_light_onTertiary,
//    tertiaryContainer = MindfulColorTheme.md_theme_light_tertiaryContainer,
//    onTertiaryContainer = MindfulColorTheme.md_theme_light_onTertiaryContainer,
//    error = MindfulColorTheme.md_theme_light_error,
//    errorContainer = MindfulColorTheme.md_theme_light_errorContainer,
//    onError = MindfulColorTheme.md_theme_light_onError,
//    onErrorContainer = MindfulColorTheme.md_theme_light_onErrorContainer,
//    background = MindfulColorTheme.md_theme_light_background,
//    onBackground = MindfulColorTheme.md_theme_light_onBackground,
//    surface = MindfulColorTheme.md_theme_light_surface,
//    onSurface = MindfulColorTheme.md_theme_light_onSurface,
//    surfaceVariant = MindfulColorTheme.md_theme_light_surfaceVariant,
//    onSurfaceVariant = MindfulColorTheme.md_theme_light_onSurfaceVariant,
//    outline = MindfulColorTheme.md_theme_light_outline,
//    inverseOnSurface = MindfulColorTheme.md_theme_light_inverseOnSurface,
//    inverseSurface = MindfulColorTheme.md_theme_light_inverseSurface,
//    inversePrimary = MindfulColorTheme.md_theme_light_inversePrimary,
//    surfaceTint = MindfulColorTheme.md_theme_light_primary,


private val LightColorScheme = lightColorScheme(

    primary = MindfulColorTheme.light_primary, // dark_orange
    onPrimary = MindfulColorTheme.light_onPrimary, // Contrasting white for text/icons on primary
    primaryContainer = MindfulColorTheme.light_primaryContainer, // light_orange
    onPrimaryContainer = MindfulColorTheme.light_onPrimaryContainer, // Contrast dark brown

    secondary = MindfulColorTheme.light_secondary, // dark_green
    onSecondary = MindfulColorTheme.light_onSecondary, // Contrasting white for text/icons on secondary
    secondaryContainer = MindfulColorTheme.light_secondaryContainer, // medium_green
    onSecondaryContainer = MindfulColorTheme.light_onSecondaryContainer, // Contrast dark green

    tertiary = MindfulColorTheme.light_tertiary, // heart_red
    onTertiary = MindfulColorTheme.light_onTertiary, // Contrasting white for text/icons on tertiary
    tertiaryContainer = MindfulColorTheme.light_tertiaryContainer, // light_red
    onTertiaryContainer = MindfulColorTheme.light_onTertiaryContainer, // Contrast dark red

    background = MindfulColorTheme.light_background, // warm_white
    onBackground = MindfulColorTheme.light_onBackground, // Neutral dark for text/icons on background

    surface = MindfulColorTheme.light_surface, // warm_white (matching background)
    onSurface = MindfulColorTheme.light_onSurface, // Neutral dark for text/icons on surface
    surfaceVariant = MindfulColorTheme.light_surfaceVariant, // light_green
    onSurfaceVariant = MindfulColorTheme.light_onSurfaceVariant, // Contrast dark green

    outline = MindfulColorTheme.light_outline, // Muted outline, could be a grayish-brown tone
    error = MindfulColorTheme.light_error, // Default Material3 error color
    onError = MindfulColorTheme.light_onError, // Contrasting white for text/icons on error
    errorContainer = MindfulColorTheme.light_errorContainer, // Default Material3 error container
    onErrorContainer = MindfulColorTheme.light_onErrorContainer, // Default Material3 on error container

    inverseSurface = MindfulColorTheme.light_inverseSurface, // Dark mode for inverse surfaces
    inverseOnSurface = MindfulColorTheme.light_inverseOnSurface, // Light contrast text/icons on inverse
    inversePrimary = MindfulColorTheme.light_inversePrimary, // light_orange

    surfaceTint = MindfulColorTheme.light_surfaceTint, // Used for surface tinting
    outlineVariant = MindfulColorTheme.light_outlineVariant, // Lighter outline
    scrim = MindfulColorTheme.light_scrim // Default scrim for modal elements
)

//)

//private val DarkColorScheme = darkColorScheme(
//    primary = MindfulColorTheme.md_theme_dark_primary,
//    onPrimary = MindfulColorTheme.md_theme_dark_onPrimary,
//    primaryContainer = MindfulColorTheme.md_theme_dark_primaryContainer,
//    onPrimaryContainer = MindfulColorTheme.md_theme_dark_onPrimaryContainer,
//    secondary = MindfulColorTheme.md_theme_dark_secondary,
//    onSecondary = MindfulColorTheme.md_theme_dark_onSecondary,
//    secondaryContainer = MindfulColorTheme.md_theme_dark_secondaryContainer,
//    onSecondaryContainer = MindfulColorTheme.md_theme_dark_onSecondaryContainer,
//    tertiary = MindfulColorTheme.md_theme_dark_tertiary,
//    onTertiary = MindfulColorTheme.md_theme_dark_onTertiary,
//    tertiaryContainer = MindfulColorTheme.md_theme_dark_tertiaryContainer,
//    onTertiaryContainer = MindfulColorTheme.md_theme_dark_onTertiaryContainer,
//    error = MindfulColorTheme.md_theme_dark_error,
//    errorContainer = MindfulColorTheme.md_theme_dark_errorContainer,
//    onError = MindfulColorTheme.md_theme_dark_onError,
//    onErrorContainer = MindfulColorTheme.md_theme_dark_onErrorContainer,
//    background = MindfulColorTheme.md_theme_dark_background,
//    onBackground = MindfulColorTheme.md_theme_dark_onBackground,
//    surface = MindfulColorTheme.md_theme_dark_surface,
//    onSurface = MindfulColorTheme.md_theme_dark_onSurface,
//    surfaceVariant = MindfulColorTheme.md_theme_dark_surfaceVariant,
//    onSurfaceVariant = MindfulColorTheme.md_theme_dark_onSurfaceVariant,
//    outline = MindfulColorTheme.md_theme_dark_outline,
//    inverseOnSurface = MindfulColorTheme.md_theme_dark_inverseOnSurface,
//    inverseSurface = MindfulColorTheme.md_theme_dark_inverseSurface,
//    inversePrimary = MindfulColorTheme.md_theme_dark_inversePrimary,
//    surfaceTint = MindfulColorTheme.md_theme_dark_primary,
//)

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
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}