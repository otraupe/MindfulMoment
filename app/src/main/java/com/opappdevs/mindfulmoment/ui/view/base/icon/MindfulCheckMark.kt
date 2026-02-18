package com.opappdevs.mindfulmoment.ui.view.base.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.opappdevs.mindfulmoment.R

@Composable
fun MindfulCheckMark(
    modifier: Modifier = Modifier,
) {
    Icon(
        imageVector = Icons.Filled.Check,
        contentDescription = stringResource(R.string.ui_base_icon_success_cd),
        modifier = modifier,
        tint = colorResource(R.color.dark_green)
    )
}