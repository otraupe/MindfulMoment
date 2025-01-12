package com.opappdevs.mindfulmoment.ui.view.base.button.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton

@Composable
fun MindfulIconButtonBack(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = stringResource(R.string.ui_base_back_button_cd),
            modifier = modifier
        )
    }
}

@ThemePreviews
@Composable
fun PreviewMindfulIconButtonBack() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        MindfulIconButtonBack { }
    }
}