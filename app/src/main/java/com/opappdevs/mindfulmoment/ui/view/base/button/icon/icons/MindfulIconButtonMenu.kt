package com.opappdevs.mindfulmoment.ui.view.base.button.icon.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme
import com.opappdevs.mindfulmoment.ui.view.base.button.icon.MindfulIconButton

@Composable
fun MindfulIconButtonMenu(
    size: Dp = 0.dp,
    onClick: () -> Unit,
) {
    MindfulIconButton(
        imageVector = Icons.Filled.Menu,
        contentDescription = stringResource(R.string.ui_base_menu_button_cd),
        onClick = onClick,
        size = size
    )
}

@ThemePreviews
@Composable
fun PreviewMindfulIconButtonMenu() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        MindfulIconButtonMenu { }
    }
}