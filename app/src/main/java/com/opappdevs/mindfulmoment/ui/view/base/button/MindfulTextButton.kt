package com.opappdevs.mindfulmoment.ui.view.base.button

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme

@Composable
fun MindfulTextButton(
    labelRes: Int,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    fontSize: Int? = null,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier.defaultMinSize(
            minWidth = dimensionResource(R.dimen.mindful_base_button_min_width),
            minHeight = dimensionResource(R.dimen.mindful_base_button_height)
        ),
        enabled = enabled,
        onClick = onClick
    ) {
        Text(
            text = stringResource(labelRes),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = fontSize?.sp ?: MaterialTheme.typography.titleMedium.fontSize,
                hyphens = Hyphens.Auto
            ),
            textAlign = TextAlign.Center
        )
    }
}

@ThemePreviews
@Composable
fun PreviewMindfulTextButton() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        MindfulTextButton(R.string.ui_base_button_dummy) { }
    }
}