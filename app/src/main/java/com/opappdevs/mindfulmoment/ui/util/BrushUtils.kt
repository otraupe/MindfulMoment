package com.opappdevs.mindfulmoment.ui.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * Provides a vertical gradient Brush designed to create a "fading edge" effect
 * at the bottom of a container. It transitions from transparent to the container's
 * surface color.
 *
 * This should be used with a background modifier to create a static overlay.
 *
 * @return A memoized `Brush` instance.
 */
@Composable
fun fadingEdgeBrush(): Brush {
    val gradientColor = MaterialTheme.colorScheme.surface
    return remember(gradientColor) {
        Brush.verticalGradient(
            0.0f to Color.Transparent,
            1.0f to gradientColor
        )
    }
}
