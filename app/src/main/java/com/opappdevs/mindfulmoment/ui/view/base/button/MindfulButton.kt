package com.opappdevs.mindfulmoment.ui.view.base.button

import androidx.compose.foundation.shape.RoundedCornerShape
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
        modifier = modifier,
        elevation = ButtonDefaults.elevatedButtonElevation(),
        onClick = onClick,
        shape = RoundedCornerShape(dimensionResource(R.dimen.mindful_rounded_corner))
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