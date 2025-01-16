package com.opappdevs.mindfulmoment.ui.view.main

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.opappdevs.mindfulmoment.navigation.Destinations
import com.opappdevs.mindfulmoment.navigation.DoubleBackToExit
import com.opappdevs.mindfulmoment.navigation.NavGraph

@Composable
fun Main() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }    // will not survive config changes
    val scope = rememberCoroutineScope()    // for basic UI transformations...
    // such as opening the nav drawer; coroutines will
    // be cancelled if the calling composable leaves
    // the composition (= is not displayed anymore)
    DoubleBackToExit(navController, scope) //TODO: only works for default/first route?
    MainNavDrawer(drawerState, scope) {
        Scaffold(
            contentWindowInsets = WindowInsets(0.dp),
            topBar = {
                if (currentRoute(navController) != Destinations.Onboarding.route) {
                    MainTopBar(drawerState, scope)
                }
            },
            snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        ) {
            contentPadding ->
            NavGraph(
                navController = navController,
                snackState = snackBarHostState,
                startDestination = Destinations.Onboarding.route, //TODO: dynamically
                modifier = Modifier.padding(contentPadding),
            )
        }
    }
}

//TODO: this doubles with NavHelper, but we need this to respond to state here

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}