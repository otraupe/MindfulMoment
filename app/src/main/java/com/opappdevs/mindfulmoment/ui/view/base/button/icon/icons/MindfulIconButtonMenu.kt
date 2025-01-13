package com.opappdevs.mindfulmoment.ui.view.base.button.icon.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme

//TODO: adapt to use MindfulIconButton

@Composable
fun MindfulIconButtonMenu(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Filled.Menu,
            contentDescription = stringResource(id = R.string.ui_main_nav_drawer_button_cd),
            modifier = modifier
        )
    }
}


@ThemePreviews
@Composable
fun PreviewMindfulIconButtonMenu() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        MindfulIconButtonMenu { }
    }
}