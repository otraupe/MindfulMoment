package com.opappdevs.mindfulmoment.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.opappdevs.mindfulmoment.ui.view.main.home.Home
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.Onboarding

/**
 * Defines the navigation graph for the application. Initializes the NavHost and displays the first
 * composable via the 'startDestination'.
 * */
@Composable
fun NavGraph(
    navController: NavHostController,       // nav controller for navigating programmatically
    snackState: SnackbarHostState,          // snack bar host state for displaying snack bars
    startDestination: String = Screens.Home.route,   // first composable to auto-navigate to
    modifier: Modifier = Modifier.fillMaxSize(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screens.Home.route) {
            Home(
                navController = navController,
                snackState = snackState
            )
        }
        composable(route = Screens.Onboarding.route) {
            Onboarding(
                navController = navController,
                snackState = snackState
            )
        }
    }
}