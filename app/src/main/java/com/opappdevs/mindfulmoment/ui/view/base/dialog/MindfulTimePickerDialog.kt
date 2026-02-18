package com.opappdevs.mindfulmoment.ui.view.base.dialog

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDialog
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulTextButton
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MindfulTimePickerDialog(
    timePickerState: TimePickerState,
    titleRes: Int?,
    confirmButtonTextRes: Int,
    dismissButtonTextRes: Int,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    onDismissRequest: () -> Unit = {}
) {
    TimePickerDialog( //TODO: You can use a standard Dialog here
        onDismissRequest = onDismissRequest,
        confirmButton = {
            MindfulButton(
                labelRes = confirmButtonTextRes
            ) {
                onConfirm()
            }
        },
        title = { Text(text = if (titleRes != null) stringResource(titleRes) else "") }, //text in dialog is badly positioned, but without it
        //..top margin is too small
        dismissButton = {
            MindfulTextButton(
                labelRes = dismissButtonTextRes
            ) {
                onDismiss()
            }
        }
    ) {
        TimePicker(state = timePickerState)
    }
}