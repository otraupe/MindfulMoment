package com.opappdevs.mindfulmoment.ui.view.basic

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.annotations.ThemePreviews

@Composable
fun MindfulTextButton(string: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(dimensionResource(R.dimen.default_rounded_corner))
    ) {
        Text(
            text = string,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@ThemePreviews
@Composable
fun PreviewMindfulTextButton() {
    MindfulTextButton("Drück mich") { }
}