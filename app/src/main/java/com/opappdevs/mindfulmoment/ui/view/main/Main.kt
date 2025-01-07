package com.opappdevs.mindfulmoment.ui.view.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.opappdevs.mindfulmoment.navigation.NavGraph
import com.opappdevs.mindfulmoment.navigation.Screens

@Composable
fun Main() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }    // will not survive config changes
    val scope = rememberCoroutineScope()    // for basic UI transformations...
    // such as opening the nav drawer; coroutines will
    // be cancelled if the calling composable leaves
    // the composition (= is not displayed anymore)
    MainNavDrawer(drawerState, scope) {
        Scaffold(
            topBar = {
                MainTopBar(drawerState, scope)
            },
            snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        ) {
            contentPadding ->
            //TODO: dynamically determine start target (based on onboarding done)
            NavGraph(
                navController = navController,
                snackState = snackBarHostState,
                startDestination = Screens.Onboarding.route,
                modifier = Modifier.padding(contentPadding),
            )
        }
    }
}