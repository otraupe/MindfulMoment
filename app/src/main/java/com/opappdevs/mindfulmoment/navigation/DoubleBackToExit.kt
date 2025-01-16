package com.opappdevs.mindfulmoment.navigation

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavHostController
import com.opappdevs.mindfulmoment.navigation.NavHelper.Companion.isExitAllowedForStack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DoubleBackToExit(
    navController: NavHostController,
    scope: CoroutineScope
) {
    val context = LocalContext.current
    var showExitToast by remember { mutableStateOf(false) }
    var lastBackPressTime by remember { mutableLongStateOf(0L) }
    var job by remember { mutableStateOf<kotlinx.coroutines.Job?>(null) }

    // Observe the back stack changes
    val visibleEntries by navController.visibleEntries.collectAsState()

    BackHandler(enabled = true) {
        if (!isExitAllowedForStack(navController, visibleEntries)) {
            navController.popBackStack() // Default back behavior
            return@BackHandler
        }
        val currentTime = System.currentTimeMillis()
        if (showExitToast && currentTime - lastBackPressTime <= 5000) {
            job?.cancel()
            showExitToast = false
            // Exit the app
            (context as? androidx.activity.ComponentActivity)?.finish()
        } else {
            lastBackPressTime = currentTime
            showExitToast = true
            val currentRoute = navController.currentBackStackEntry?.destination?.route
            val message = if (currentRoute == "Onboarding") {
                "Onboarding is not complete. Data might be lost.\n\nPress back again to exit"
            } else {
                "Press back again to exit"
            }
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

            job = scope.launch {
                delay(5000)
                showExitToast = false
            }
        }
    }

    // Reset showExitToast when lifecycle is ON_STOP
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                job?.cancel()
                showExitToast = false
            }
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}