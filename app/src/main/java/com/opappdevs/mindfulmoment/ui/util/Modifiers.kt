package com.opappdevs.mindfulmoment.ui.util

import androidx.compose.foundation.ScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.verticalColumnScrollbar(
    scrollState: ScrollState,
    width: Dp = 4.dp,
    showScrollBarTrack: Boolean = true,
    scrollBarTrackColor: Color = MaterialTheme.colorScheme.surface,
    scrollBarColor: Color = MaterialTheme.colorScheme.primary,
): Modifier {
    return drawWithContent {
        // Draw the column's content
        drawContent()
        // Dimensions and calculations
        val viewportHeight = this.size.height
        val totalContentHeight = scrollState.maxValue.toFloat() + viewportHeight
        // Ignore if nothing to scroll
        if (totalContentHeight > viewportHeight) {
            // Draw the track (optional)
            if (showScrollBarTrack) {
                drawRoundRect(
                    cornerRadius = CornerRadius(width.toPx()/2),
                    color = scrollBarTrackColor,
                    topLeft = Offset(this.size.width - width.toPx(), 0f),
                    size = Size(width.toPx(), viewportHeight),
                )
            }
            // Get the scrollbar position
            val scrollValue = scrollState.value.toFloat()
            // Compute scrollbar height and position
            val scrollBarHeight =
                (viewportHeight / totalContentHeight) * viewportHeight
            val scrollBarStartOffset =
                (scrollValue / totalContentHeight) * viewportHeight
            // Draw the scrollbar
            drawRoundRect(
                cornerRadius = CornerRadius(width.toPx()/2),
                color = scrollBarColor,
                topLeft = Offset(this.size.width - width.toPx(), scrollBarStartOffset),
                size = Size(width.toPx(), scrollBarHeight)
            )
        }
    }
}