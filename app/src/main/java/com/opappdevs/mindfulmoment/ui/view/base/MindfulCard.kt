package com.opappdevs.mindfulmoment.ui.view.base

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme

@Composable
fun MindfulCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit)
{
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        content()
    }
}

@ThemePreviews
@Composable
fun PreviewMindfulCard() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        MindfulCard {  }
    }
}