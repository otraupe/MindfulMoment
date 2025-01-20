package com.opappdevs.mindfulmoment.ui.view.base.button

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme

@Composable
fun MindfulButton(
    labelRes: Int,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.defaultMinSize(
            minWidth = dimensionResource(R.dimen.mindful_base_button_min_width),
            minHeight = dimensionResource(R.dimen.mindful_base_button_height)
        ),
        enabled = enabled,
        onClick = onClick,
    ) {
        Text(
            text = stringResource(labelRes),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@ThemePreviews
@Composable
fun PreviewMindfulButton() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        MindfulButton(R.string.ui_base_button_dummy) { }
    }
}