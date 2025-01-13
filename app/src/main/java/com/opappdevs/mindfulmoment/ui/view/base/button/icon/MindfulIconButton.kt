package com.opappdevs.mindfulmoment.ui.view.base.button.icon

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp

@Composable
fun MindfulIconButton(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    size: Dp
) {
    IconButton(
        modifier = Modifier.height(size)
            .aspectRatio(
                ratio = 1f,
                matchHeightConstraintsFirst = true
            ),
        onClick = onClick
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize()
        )
    }
}