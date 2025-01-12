package com.opappdevs.mindfulmoment.ui.view.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme

@Composable
fun MindfulCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit)
{
    Card(
        modifier = modifier.padding(dimensionResource(R.dimen.mindful_page_padding)),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.surfaceVariant,
//        ),
    ) {
        Box(
            modifier = Modifier.padding(dimensionResource(R.dimen.mindful_page_padding))
        ) {
            content()
        }
    }
}

@ThemePreviews
@Composable
fun PreviewMindfulCard() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        MindfulCard {  }
    }
}