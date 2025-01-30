package com.opappdevs.mindfulmoment.ui.view.base.permission

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton
import timber.log.Timber

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun permissionButton(
    labelRes: Int,
    permission: Permission,
    uiVisibleState: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    getPermissionRequestedBefore: () -> Boolean,
    setPermissionRequestedBefore: () -> Unit,
    onClick: () -> Unit
): PermissionState? {
    val context = LocalContext.current
    var showRationaleDialog by remember { mutableStateOf(false) }
    var showSettingsDialog by remember { mutableStateOf(false) }
    var permissionResultNotHandled by remember { mutableStateOf(false) }

    //TODO: extract all strings

    // This observes the lifecycle to handle app coming back from settings
    val lifecycleOwner = LocalLifecycleOwner.current //onboarding destination
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            Timber.d("Lifecycle event $event from source $source")
            if (event == Lifecycle.Event.ON_RESUME && permissionResultNotHandled) {
                if (ContextCompat.checkSelfPermission(context, permission.id)
                    == PackageManager.PERMISSION_GRANTED
                ) {
                    Toast.makeText(context, "Granted.", Toast.LENGTH_SHORT).show()
                    uiVisibleState.value = true
                } else {
                    Toast.makeText(context, "Not granted.", Toast.LENGTH_SHORT).show()
                    uiVisibleState.value = false
                }
                permissionResultNotHandled = false
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val permissionState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberPermissionState(Manifest.permission.POST_NOTIFICATIONS) { isGranted ->
            if (isGranted) {
                uiVisibleState.value = true
                //TODO: advance pager after animation
            } else {
                if (permissionResultNotHandled) {
                    Toast.makeText(context, "Permission denied.", Toast.LENGTH_SHORT).show()
                }
//                else {
//                    showSettingsDialog = true
//                }
            }
            permissionResultNotHandled = false // Reset on every permission state change
        }
    } else {
        null
    }

    val requestPermissionLambda =  {
        permissionResultNotHandled = true
        setPermissionRequestedBefore()
        permissionState?.launchPermissionRequest()
    }

    MindfulButton(
        labelRes = labelRes,
        modifier = modifier,
        onClick = {
            permissionState?.status?.let {
                when {
                    it.isGranted -> onClick()
                    it.shouldShowRationale -> showRationaleDialog = true
                    getPermissionRequestedBefore() -> showSettingsDialog = true
                    else -> requestPermissionLambda()
                }
                return@MindfulButton
            }
            // TODO: store notification time, also advance pager
            onClick()
        })

    if (showRationaleDialog) {
        AlertDialog(
            onDismissRequest = { showRationaleDialog = false },
            title = { Text("Permission Required") },
            text = { Text(stringResource(permission.rationaleRes)) },
            confirmButton = {
                Button(onClick = {
                    showRationaleDialog = false
                    requestPermissionLambda()
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showRationaleDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Handle the result of the permission request, particularly when coming back from settings
    LaunchedEffect(permissionState?.status) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            //TODO: not on first arrival
//            if (permissionState?.status?.isGranted == false && !permissionState.status.shouldShowRationale) {
//                // Permission permanently denied, show dialog to guide the user to the app settings.
//                showSettingsDialog = true
//            }
        }
    }

    if (showSettingsDialog) {
        AlertDialog(
            onDismissRequest = { showSettingsDialog = false },
            title = { Text("Permission Denied") },
            text = { Text("You have denied notification permission. To enable it, please go to app settings.") },
            confirmButton = {
                //TODO: Mindful Buttons?
                Button(onClick = {
                    // Open app settings
                    showSettingsDialog = false
                    permissionResultNotHandled = true
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", context.packageName, null)
                    intent.data = uri
                    context.startActivity(intent)
                }) {
                    Text("Yes, please")
                }
            },
            dismissButton = {
                TextButton(onClick = { showSettingsDialog = false }) {
                    Text("No, thanks")
                }
            }
        )
    }
    return permissionState
}

@OptIn(ExperimentalPermissionsApi::class)
@Preview
@Composable
fun PreviewNotificationPermissionScreen() {
    permissionButton(
        labelRes = R.string.ui_onboarding_pages_notifications_button_primary,
        permission = Permission.NOTIFICATION,
        uiVisibleState = remember { mutableStateOf(false) },
        modifier = Modifier,
        getPermissionRequestedBefore = { false },
        setPermissionRequestedBefore = { },
        onClick = {}
    )
}