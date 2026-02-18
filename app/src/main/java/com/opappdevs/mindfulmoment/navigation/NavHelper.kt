package com.opappdevs.mindfulmoment.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import timber.log.Timber

class NavHelper {
    companion object {
        fun currentRoute(navController: NavHostController): String? {
            val navBackStackEntry = navController.currentBackStackEntry
            return navBackStackEntry?.destination?.route
        }

        fun isExitAllowedForStack(
            navController: NavHostController,
            entries: List<NavBackStackEntry>
        ): Boolean {
            val allowedRoutes = listOf(Destinations.Onboarding.route, Destinations.Home.route)
            val currentRoute = currentRoute(navController)
            Timber.d("Current route is $currentRoute " +
                    "and routes allowed for exiting the app are $allowedRoutes")
            if (currentRoute != null) {
                val currentEntryIsAllowed = allowedRoutes.contains(currentRoute)
                return entries.count() == 1 && currentEntryIsAllowed //visible entries, not containing nav graph
            } else {
                return false
            }
        }
    }
}

