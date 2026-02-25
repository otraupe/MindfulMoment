package com.opappdevs.mindfulmoment.navigation

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.opappdevs.mindfulmoment.ui.view.main.home.Home
import com.opappdevs.mindfulmoment.ui.view.main.legal.Imprint
import com.opappdevs.mindfulmoment.ui.view.main.legal.PrivacyPolicy
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.Onboarding

/**
 * Defines the navigation graph for the application. Initializes the NavHost and displays the first
 * composable via the 'startDestination'.
 * */
@Composable
fun NavGraph(
    navController: NavHostController,       // nav controller for navigating programmatically
    snackState: SnackbarHostState,          // snack bar host state for displaying snack bars
    modifier: Modifier = Modifier,
    startDestination: String = Destinations.Home.route,   // first composable to auto-navigate to
) {
    val baseTransitionMillis = remember { 500 }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            fadeIn(
                animationSpec = tween(baseTransitionMillis)
            ) + slideIn(
                animationSpec = tween(baseTransitionMillis),
                initialOffset = { fullSize -> IntOffset(fullSize.width,0) }
            )
                          },
        exitTransition = {
            fadeOut(
                animationSpec = tween(baseTransitionMillis),
                targetAlpha = 0f
            ) + scaleOut(
                animationSpec = tween(baseTransitionMillis),
                targetScale = .75f
            )
                         },
        popEnterTransition = {
            fadeIn(
                animationSpec = tween(baseTransitionMillis),
                initialAlpha = 0f
            ) + scaleIn(
                animationSpec = tween(baseTransitionMillis),
                initialScale = .75f
            )
                             },
        popExitTransition = {
            fadeOut(
                animationSpec = tween(baseTransitionMillis)
            ) + slideOut(
                animationSpec = tween(baseTransitionMillis),
                targetOffset = { fullSize -> IntOffset(fullSize.width,0) }
            )
        }
    ) {
        composable(
            route = Destinations.Home.route,
        ) {
            Home(
                navController = navController,
                snackState = snackState
            )
        }
        composable(
            route = Destinations.Onboarding.route,
            exitTransition = { ExitTransition.None }, //race condition with pop from backstack?
            popExitTransition = { ExitTransition.None }
        ) {
            Onboarding(
                navController = navController,
                snackState = snackState
            )
        }
        composable(
            route = Destinations.Imprint.route,
        ) {
            Imprint(navController = navController)
        }
        composable(
            route = Destinations.PrivacyPolicy.route,
        ) {
            PrivacyPolicy(navController = navController)
        }
    }
}