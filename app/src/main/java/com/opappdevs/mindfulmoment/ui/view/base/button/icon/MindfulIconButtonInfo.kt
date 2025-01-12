package com.opappdevs.mindfulmoment.ui.view.base.button.icon

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme

@Composable
fun MindfulIconButtonInfo(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier.aspectRatio(
            ratio = 1f,
            matchHeightConstraintsFirst = true
        ),
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = stringResource(R.string.ui_base_info_button_cd),
            modifier = Modifier.fillMaxSize()
        )
    }
}

@ThemePreviews
@Composable
fun PreviewMindfulIconButtonInfo() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        MindfulIconButtonInfo(
            modifier = Modifier.height(32.dp)
        ) { }
    }
}