package com.opappdevs.mindfulmoment.ui.view.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

//TODO: overloads for color, shape (e.g. gradient), and image background

@Composable
fun MindfulBackground(
    modifier: Modifier = Modifier.fillMaxSize(),
    background: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        background()
        content()
    }
}