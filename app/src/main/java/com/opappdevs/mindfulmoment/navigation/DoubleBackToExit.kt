package com.opappdevs.mindfulmoment.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.integerResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.navigation.NavHelper.Companion.isExitAllowedForStack
import com.opappdevs.mindfulmoment.ui.view.base.MindfulToast
import com.opappdevs.mindfulmoment.ui.view.base.MindfulToast.Companion.showMindfulToast
import com.opappdevs.mindfulmoment.ui.view.base.MindfulToast.Companion.Duration.SHORT
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DoubleBackToExit(
    navController: NavHostController,
    scope: CoroutineScope
) {
    val context = LocalContext.current
    var showExitToast by remember { mutableStateOf(true) }
    var lastBackPressTime by remember { mutableLongStateOf(0L) }
    var job by remember { mutableStateOf<kotlinx.coroutines.Job?>(null) }

    val doubleBackWindow = integerResource(R.integer.ui_double_back_exit_window)

    // Observe the back stack changes
    val visibleEntries by navController.visibleEntries.collectAsState()

    BackHandler(enabled = true) {
        if (!isExitAllowedForStack(navController, visibleEntries)) {
            navController.popBackStack() // Default back behavior
            return@BackHandler
        }
        if (!showExitToast) { // lifecycle stoppen (activity?)
            return@BackHandler
        }
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastBackPressTime <= doubleBackWindow) {
            job?.cancel()
            showExitToast = false
            // Exit the app
            (context as? androidx.activity.ComponentActivity)?.finish()
        } else {
            lastBackPressTime = currentTime
            showMindfulToast(
                context = context,
                messageRes = R.string.ui_navigation_double_back_exit,
                duration = SHORT
            )
            job = scope.launch {
                delay(doubleBackWindow.toLong())
                showExitToast = true
            }
        }
    }

    // Reset showExitToast when lifecycle is ON_STOP
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_STOP -> {
                    job?.cancel()
                    showExitToast = false
                }
                Lifecycle.Event.ON_START -> {
                    showExitToast = true
                }
                else -> {}
            }
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}