package com.opappdevs.mindfulmoment.ui.view.basic.pagers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.opappdevs.mindfulmoment.ui.theme.MindfulColorTheme

@Composable
fun AppBackground(
    modifier: Modifier = Modifier.fillMaxSize(),
    content: @Composable () -> Unit
) {
    val bgGradient = remember {
        Brush.linearGradient(listOf(
            MindfulColorTheme.light_background,
            MindfulColorTheme.light_primaryContainer,
        ))
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = bgGradient),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}