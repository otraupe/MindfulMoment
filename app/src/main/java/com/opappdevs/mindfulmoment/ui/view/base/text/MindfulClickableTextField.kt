package com.opappdevs.mindfulmoment.ui.view.base.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opappdevs.mindfulmoment.R

@Composable
fun MindfulClickableTextField(
    labelRes: Int,
    textValue: String,
    onClick: () -> Unit
) {
    OutlinedTextField(
        value = textValue,
        onValueChange = {}, // No-op, enabled is false
        enabled = false,
        label = {
            Text(
                text = stringResource(labelRes),
                maxLines = 1,
                autoSize = TextAutoSize.StepBased(
                    minFontSize = 12.sp,
                    maxFontSize = 18.sp
                )
            )
        },
        textStyle = LocalTextStyle.current.copy(
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
        ),
        singleLine = true,
        modifier = Modifier
            .width(dimensionResource(R.dimen.mindful_base_textField_width))
            .padding(top = dimensionResource(R.dimen.mindful_base_card_sub_spacing))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors().copy(
            disabledTextColor = MaterialTheme.colorScheme.onSurface,
            disabledIndicatorColor = MaterialTheme.colorScheme.outline,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            //For Icons
            disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant)
    )
}