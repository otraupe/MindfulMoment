package com.opappdevs.mindfulmoment.ui.view.base.button

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme

@Composable
fun MindfulButton(
    string: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.defaultMinSize(
            minWidth = dimensionResource(R.dimen.mindful_base_button_min_width),
            minHeight = dimensionResource(R.dimen.mindful_base_button_height)
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(),
        onClick = onClick,
    ) {
        Text(
            text = string,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@ThemePreviews
@Composable
fun PreviewMindfulButton() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        MindfulButton("Drück mich") { }
    }
}