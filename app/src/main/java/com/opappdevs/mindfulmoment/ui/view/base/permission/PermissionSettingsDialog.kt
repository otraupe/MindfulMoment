package com.opappdevs.mindfulmoment.ui.view.base.permission

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import com.opappdevs.mindfulmoment.R

//TODO: generalize (use DialogButton)

@Composable
fun PermissionSettingsDialog(
    context: Context,
    visibleState: MutableState<Boolean>,
    titleRes: Int,
    bodyRes: Int,
    setting: String,
//    yesButton: DialogButton,
//    noButton: DialogButton
) {
    AlertDialog(
        onDismissRequest = { visibleState.value = false },
        title = { Text(text = stringResource(titleRes)) },
        text = { Text(text = stringResource(bodyRes)) },
        confirmButton = {
            Button(onClick = {
                    visibleState.value = false
                    // Open app settings
                    val intent = Intent(setting)
                    val uri = Uri.fromParts("package", context.packageName, null)
                    intent.data = uri
                    context.startActivity(intent)
                }) {
                Text(text = stringResource(R.string.ui_permissions_dialog_yes))
            }
        },
        dismissButton = {
            TextButton(onClick = { visibleState.value = false }) {
                Text(text = stringResource(R.string.ui_permissions_dialog_no))
            }
        }
    )
}