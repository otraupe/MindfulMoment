package com.opappdevs.mindfulmoment.ui.view.base.permissions

import android.content.Intent
import android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.ui.view.base.MindfulToast.Companion.Duration.LONG
import com.opappdevs.mindfulmoment.ui.view.base.MindfulToast.Companion.showMindfulToast
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton
import com.opappdevs.mindfulmoment.ui.view.base.dialog.MindfulAlertDialog
import timber.log.Timber

@Composable
fun PermissionAlarmsButton(
    labelRes: Int,
    uiVisibleState: MutableState<Boolean>,
    uiAnimateState: MutableState<Boolean>,
    canScheduleExactAlarms: () -> Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    var permissionResultNotHandled by remember { mutableStateOf(false) }

    var (showHintDialog, setShowHintDialog) = remember { mutableStateOf(false) }

    // This observes the lifecycle to handle app response coming back from settings
    val lifecycleOwner = LocalLifecycleOwner.current //onboarding destination
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            Timber.d("Lifecycle event $event from source $source")
            if (event == Lifecycle.Event.ON_RESUME && permissionResultNotHandled) {
                if (canScheduleExactAlarms()) {
                    Timber.d("DisposableEffect: Permission granted")
                    uiVisibleState.value = true
                } else {
                    Timber.d( "DisposableEffect: Permission not granted")
                    uiVisibleState.value = false //just in case
                }
                permissionResultNotHandled = false
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val requestPermissionLambda =  {
        Timber.d("debug1 | requestPermissionLambda")
        permissionResultNotHandled = true
        uiAnimateState.value = true
        try {
            context.startActivity(Intent(ACTION_REQUEST_SCHEDULE_EXACT_ALARM))
        } catch (t: Throwable) {
            Timber.e(t)
            showMindfulToast(
                context = context,
                messageRes = R.string.ui_base_error_unspecified,
                duration = LONG
            )
        }
    }

    if (showHintDialog) {
        MindfulAlertDialog(
            titleRes = R.string.ui_onboarding_pages_alarms_dialog_title,
            textRes = R.string.ui_onboarding_pages_alarms_dialog_text,
            confirmButtonTextRes = R.string.ui_base_button_ok,
            dismissButtonTextRes = R.string.ui_base_button_cancel,
            onConfirm = {
                setShowHintDialog(false)
                requestPermissionLambda()
            },
            onDismiss = { setShowHintDialog(false) },
            onDismissRequest = { setShowHintDialog(false) }
        )
    }

    MindfulButton(
        labelRes = labelRes,
        modifier = modifier,
        enabled = enabled,
        onClick = {
            if (!canScheduleExactAlarms()) {
                setShowHintDialog(true)
            } else {
                onClick()
            }
        }
    )

    // Respond to permission already granted on initial visit (although this should not happen)
    LaunchedEffect(Unit) {
        if (canScheduleExactAlarms()) {
            uiVisibleState.value = true
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Preview
@Composable
fun PreviewPermissionAlarmsButton() {
    PermissionAlarmsButton(
        labelRes = R.string.ui_onboarding_pages_notifications_button_primary,
        uiVisibleState = remember { mutableStateOf(false) },
        uiAnimateState = remember { mutableStateOf(false) },
        canScheduleExactAlarms = { true },
        modifier = Modifier,
        onClick = {}
    )
}