package com.opappdevs.mindfulmoment.ui.view.base.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulTextButton

@Composable
fun MindfulAlertDialog(
    titleRes: Int,
    textRes: Int,
    confirmButtonTextRes: Int,
    dismissButtonTextRes: Int,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    onDismissRequest: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(stringResource(titleRes)) },
        text = {
            Text(
                text = stringResource(textRes),
                style = LocalTextStyle.current.copy(fontSize = 18.sp)
            )
               },
        confirmButton = {
            MindfulButton(
                labelRes = confirmButtonTextRes,
                onClick = onConfirm
            )
        },
        dismissButton = {
            MindfulTextButton(
                labelRes = dismissButtonTextRes,
                onClick = onDismiss
            )
        }
    )
}