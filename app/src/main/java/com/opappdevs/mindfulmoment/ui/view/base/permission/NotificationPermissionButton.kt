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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun notificationPermissionButton(
    labelRes: Int,
    modifier: Modifier = Modifier
): PermissionState? {
    val context = LocalContext.current
    var showRationaleDialog by remember { mutableStateOf(false) }
    var showSettingsDialog by remember { mutableStateOf(false) }
    var rationaleMessage by remember { mutableStateOf("") }

    // This observes the lifecycle to handle app coming back from settings
    var permissionResultHandled by remember { mutableStateOf(false) }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME && !permissionResultHandled) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.POST_NOTIFICATIONS
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        // Permission is granted
                        Toast.makeText(context, "Notification permission granted.", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        // Permission is not granted
                        Toast.makeText(
                            context,
                            "Notification permission is not granted.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    permissionResultHandled = true
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val permissionState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberPermissionState(Manifest.permission.POST_NOTIFICATIONS) { isGranted ->
            permissionResultHandled = false // Reset on every permission request result
            if (isGranted) {
                Toast.makeText(context, "Notification permission granted.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(context, "Notification permission denied.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    } else {
        null
    }

    MindfulButton(
        labelRes = labelRes,
        modifier = modifier,
        onClick = {
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                    when {
                        permissionState?.status?.isGranted == true -> {
                            // Permission is already granted
                            Toast.makeText(
                                context,
                                "Notification permission is already granted.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        permissionState?.status?.shouldShowRationale == true -> {
                            // Show rationale
                            rationaleMessage =
                                "To use this feature, we need permission to send notifications. Please grant the permission."
                            showRationaleDialog = true
                        }

                        else -> {
                            // Request permission
                            permissionState?.launchPermissionRequest()
                        }
                    }
                }

                else -> {
                    // No need to ask for permission on older versions
                    Toast.makeText(
                        context,
                        "No need to ask for notification permission on this Android version.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

    if (showRationaleDialog) {
        AlertDialog(
            onDismissRequest = { showRationaleDialog = false },
            title = { Text("Permission Required") },
            text = { Text(rationaleMessage) },
            confirmButton = {
                Button(onClick = {
                    showRationaleDialog = false
                    permissionState?.launchPermissionRequest()
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = { showRationaleDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Handle the result of the permission request, particularly when coming back from settings
    LaunchedEffect(permissionState?.status) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (permissionState?.status?.isGranted == false && !permissionState.status.shouldShowRationale) {
                // Permission permanently denied, show dialog to guide the user to the app settings.
                showSettingsDialog = true
            }
        }
    }

    if (showSettingsDialog) {
        AlertDialog(
            onDismissRequest = { showSettingsDialog = false },
            title = { Text("Permission Denied") },
            text = { Text("You have denied notification permission. To enable it, please go to app settings.") },
            confirmButton = {
                Button(onClick = {
                    // Open app settings
                    showSettingsDialog = false
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", context.packageName, null)
                    intent.data = uri
                    context.startActivity(intent)
                }) {
                    Text("Yes, please")
                }
            },
            dismissButton = {
                Button(onClick = { showSettingsDialog = false }) {
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
    notificationPermissionButton(
        labelRes = R.string.ui_onboarding_pages_notifications_button_primary,
        modifier = Modifier
    )
}