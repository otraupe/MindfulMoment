package com.opappdevs.mindfulmoment.ui.view.base.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme

@Composable
fun MindfulTextButton(
    string: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier.defaultMinSize(
            minWidth = dimensionResource(R.dimen.mindful_base_button_min_width),
            minHeight = dimensionResource(R.dimen.mindful_base_button_height)
        ),
        onClick = onClick
    ) {
        Text(
            text = string,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@ThemePreviews
@Composable
fun PreviewMindfulTextButton() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        MindfulTextButton("Drück mich") { }
    }
}