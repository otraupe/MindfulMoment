package com.opappdevs.mindfulmoment.ui.view.base.permission

import android.os.Build
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.ui.view.base.MindfulToast.Companion.showMindfulToast
import com.opappdevs.mindfulmoment.ui.view.base.MindfulToast.Companion.Duration.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun permissionHandlerFucked(
    permission: Permission
): PermissionState? {
    val context = LocalContext.current
//    var showRationaleDialog by remember { mutableStateOf(false) }
    val showSettingsDialog = remember { mutableStateOf(false) }

    // This observes the lifecycle to handle app coming back from settings
    var permissionResultHandled by remember { mutableStateOf(false) }
//    val lifecycleOwner = LocalLifecycleOwner.current
//    DisposableEffect(lifecycleOwner) {
//        val observer = LifecycleEventObserver { _, event ->
//            if (event == Lifecycle.Event.ON_RESUME && !permissionResultHandled) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { //TODO: pass argument/update enum
//                    if (ContextCompat.checkSelfPermission(
//                            context,
//                            permission.id
//                        ) == PackageManager.PERMISSION_GRANTED
//                    ) {
//                        //TODO: update green check mark and buttons instead of Toast
//                        //  pass state to this screen and handle externally
//                        Toast.makeText(context, "Notification permission granted.",
//                            Toast.LENGTH_SHORT)
//                            .show()
//                    } else {
//                        // Permission is not granted
//                        Toast.makeText(
//                            context,
//                            "Notification permission is not granted.", //TODO: extract
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                    permissionResultHandled = true
//                }
//            }
//        }
//        lifecycleOwner.lifecycle.addObserver(observer)
//        onDispose {
//            lifecycleOwner.lifecycle.removeObserver(observer)
//        }
//    }

    val permissionState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberPermissionState(permission.id) { isGranted ->
            permissionResultHandled = false // Reset on every permission request result //Todo: ?
            if (isGranted) {
                //check mark will appear
            } else {
                showMindfulToast(context, R.string.ui_permissions_denied, SHORT)
            }
        }
    } else {
        null
    }

//    Column {
//        Button(onClick = {
//            when {
//                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
//                    when {
//                        permissionState?.status?.isGranted == true -> {
//                            // Permission is already granted
//                            Toast.makeText(
//                                context,
//                                "Notification permission is already granted.",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//
//                        permissionState?.status?.shouldShowRationale == true -> {
//                            // Show rationale
//                            rationaleMessage =
//                                "To use this feature, we need permission to send notifications. Please grant the permission."
//                            showRationaleDialog = true
//                        }
//
//                        else -> {
//                            // Request permission
//                            permissionState?.launchPermissionRequest()
//                        }
//                    }
//                }
//
//                else -> {
//                    // No need to ask for permission on older versions
//                    Toast.makeText(
//                        context,
//                        "No need to ask for notification permission on this Android version.",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }) {
//            Text("Request Notification Permission")
//        }
//    }

//    if (showRationaleDialog) {
//        AlertDialog(
//            onDismissRequest = { showRationaleDialog = false },
//            title = { Text("Permission Required") },
//            text = { Text(rationaleMessage) },
//            confirmButton = {
//                Button(onClick = {
//                    showRationaleDialog = false
//                    permissionState?.launchPermissionRequest()
//                }) {
//                    Text("OK")
//                }
//            },
//            dismissButton = {
//                Button(onClick = { showRationaleDialog = false }) {
//                    Text("Cancel")
//                }
//            }
//        )
//    }

    // TODO: allow enabling/disabling permission in the settings
    // Handle the result of the permission request, particularly when coming back from settings
    LaunchedEffect(permissionState?.status) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (permissionState?.status?.isGranted == false && !permissionState.status.shouldShowRationale) {
                // Permission permanently denied, show dialog to guide the user to the app settings.
                showSettingsDialog.value = true
            }
        }
    }

    if (showSettingsDialog.value) {
        PermissionSettingsDialog(
            context = context,
            visibleState = showSettingsDialog,
            titleRes = R.string.ui_permissions_denied_permanently,
            bodyRes = R.string.ui_permissions_notifications_settings_body,
            setting = Settings.ACTION_APP_NOTIFICATION_SETTINGS,
        )
    }

    return permissionState
}