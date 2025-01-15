package com.opappdevs.mindfulmoment.ui.view.base.button.icon.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme
import com.opappdevs.mindfulmoment.ui.view.base.button.icon.MindfulIconButton

@Composable
fun MindfulIconButtonClose(
    size: Dp = 32.dp,
    contentDescription: String = stringResource(
        R.string.ui_base_back_button_cd
    ),
    onClick: () -> Unit,
) {
    MindfulIconButton(
        imageVector = Icons.Outlined.Close,
        contentDescription = contentDescription,
        onClick = onClick,
        size = size
    )
}

@ThemePreviews
@Composable
fun PreviewMindfulIconButtonClose() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        MindfulIconButtonClose { }
    }
}