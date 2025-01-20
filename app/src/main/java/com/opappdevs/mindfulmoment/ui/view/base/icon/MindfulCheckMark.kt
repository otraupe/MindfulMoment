package com.opappdevs.mindfulmoment.ui.view.base.icon

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.opappdevs.mindfulmoment.R

@Composable
fun MindfulCheckMark(size: Dp = dimensionResource(R.dimen.mindful_base_button_height)) {
    Icon(
        imageVector = Icons.Filled.Check,
        contentDescription = stringResource(R.string.ui_base_icon_permission_granted_cd),
        modifier = Modifier.size(size),
        tint = colorResource(R.color.dark_green)
    )
}