package com.opappdevs.mindfulmoment.ui.view.base.permissions

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
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
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.rememberLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.ui.view.base.MindfulToast.Companion.Duration.SHORT
import com.opappdevs.mindfulmoment.ui.view.base.MindfulToast.Companion.showMindfulToast
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton
import com.opappdevs.mindfulmoment.ui.view.base.dialog.MindfulAlertDialog
import timber.log.Timber

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionButton(
    labelRes: Int,
    permission: Permissions,
    uiVisibleState: MutableState<Boolean>,
    uiAnimateState: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    getPermissionRequestedBefore: () -> Boolean,
    setPermissionRequestedBefore: () -> Unit,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    var showRationaleDialog by remember { mutableStateOf(false) }
    var showSettingsDialog by remember { mutableStateOf(false) }
    var permissionResultNotHandled by remember { mutableStateOf(false) }
    var permissionOnceDenied by remember { mutableStateOf(false) }

    // This observes the lifecycle to handle app response coming back from settings
    // This LifecycleOwner is automatically moved to DESTROYED when
    // it leaves composition and its maxLifecycle is the maximum of either
    // the maxLifecycle you set or the Lifecycle.State of the parentLifecycleOwner (-> Onboarding)
    val lifecycleOwner = rememberLifecycleOwner()
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            Timber.d("Lifecycle event $event from source $source")
            if (event == Lifecycle.Event.ON_RESUME && permissionResultNotHandled) {
                if (ContextCompat.checkSelfPermission(context, permission.id)
                    == PackageManager.PERMISSION_GRANTED
                ) {
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

    val permissionState = rememberPermissionState(permission.id) { isGranted ->
        if (isGranted) {
            Timber.d("debug1 | rememberPermissionState: Permission granted")
            uiVisibleState.value = true
        } else {
            Timber.d("debug1 | rememberPermissionState: Permission not granted")
            if (permissionResultNotHandled) {
                if (permissionOnceDenied) {
                    showMindfulToast(
                        context = context,
                        messageRes = R.string.ui_permissions_denied,
                        duration = SHORT
                    )
                } else {
                    permissionOnceDenied = true
                }
            }
        }
        permissionResultNotHandled = false // Reset on every permission state change
    }

    val requestPermissionLambda =  {
        Timber.d("debug1 | requestPermissionLambda")
        permissionResultNotHandled = true
        setPermissionRequestedBefore()
        uiAnimateState.value = true
        permissionState.launchPermissionRequest()
    }

    MindfulButton(
        labelRes = labelRes,
        modifier = modifier,
        enabled = enabled,
        onClick = {
            permissionState.status.let {
                when {
                    it.isGranted -> onClick()
                    it.shouldShowRationale -> showRationaleDialog = true
                    getPermissionRequestedBefore() -> showSettingsDialog = true
                    else -> requestPermissionLambda()
                }
            }
        }
    )

    if (showRationaleDialog) {
        MindfulAlertDialog(
            titleRes = R.string.ui_permissions_required,
            textRes = permission.rationaleRes,
            confirmButtonTextRes = R.string.ui_base_button_ok,
            dismissButtonTextRes = R.string.ui_base_button_cancel,
            onConfirm = {
                showRationaleDialog = false
                requestPermissionLambda()
            },
            onDismiss = { showRationaleDialog = false },
            onDismissRequest = { showRationaleDialog = false }
        )
    }

    // Respond to permission already granted on initial visit
    LaunchedEffect(permissionState) {
        if (permissionState.status.isGranted) {
            uiVisibleState.value = true
        }
    }

    if (showSettingsDialog) {
        MindfulAlertDialog(
            titleRes = R.string.ui_permissions_denied_permanently,
            textRes = R.string.ui_permissions_denied_permanently_instructions,
            confirmButtonTextRes = R.string.ui_base_button_yes_please,
            dismissButtonTextRes = R.string.ui_base_button_no_thanks,
            onConfirm = {
                showSettingsDialog = false
                permissionResultNotHandled = true
                val intent: Intent = if (permission.id == Manifest.permission.POST_NOTIFICATIONS) {
                    Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                        .apply {
                            putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                        }
                } else {
                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        .apply {
                            data = Uri.fromParts("package", context.packageName, null)
                        }
                }
                context.startActivity(intent)
            },
            onDismiss = { showSettingsDialog = false },
            onDismissRequest = { showSettingsDialog = false }
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Preview
@Composable
fun PreviewPermissionButton() {
    PermissionButton(
        labelRes = R.string.ui_onboarding_pages_notifications_button_primary,
        permission = Permissions.POST_NOTIFICATION,
        uiVisibleState = remember { mutableStateOf(false) },
        uiAnimateState = remember { mutableStateOf(false) },
        modifier = Modifier,
        getPermissionRequestedBefore = { false },
        setPermissionRequestedBefore = { },
        onClick = {}
    )
}