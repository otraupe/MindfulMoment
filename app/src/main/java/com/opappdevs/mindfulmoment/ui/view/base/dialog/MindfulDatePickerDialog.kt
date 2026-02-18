package com.opappdevs.mindfulmoment.ui.view.base.dialog

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulTextButton

@Composable
fun MindfulDatePickerDialog(
    datePickerState: DatePickerState,
    titleRes: Int?,
    confirmButtonTextRes: Int,
    dismissButtonTextRes: Int,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    onDismissRequest: () -> Unit = {}
) {
    DatePickerDialog( //TODO: You can probably use a standard Dialog here too
        onDismissRequest = onDismissRequest,
        confirmButton = {
            MindfulButton (
                labelRes = confirmButtonTextRes,
                modifier = Modifier.padding(
                    bottom = 16.dp, end = 16.dp
                )
            ) {
                onConfirm()
            }
        },
        dismissButton = {
            MindfulTextButton(
                labelRes = dismissButtonTextRes
            ) {
                onDismiss()
            }
        }
    ) {
        DatePicker(
            state = datePickerState,
            title = { if (titleRes != null) Text(stringResource(titleRes)) }
        )
    }
}