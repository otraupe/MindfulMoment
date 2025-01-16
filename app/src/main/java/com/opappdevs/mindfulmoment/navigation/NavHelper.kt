package com.opappdevs.mindfulmoment.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

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
//        val backStackEntryCount = backStackEntries.count()
//        val onlyAllowedRoutePresent = navController.currentBackStack.value.all { entry ->
//            allowedRoutes.contains(entry.destination.route)
//        }
//        return backStackEntryCount <= 1 && onlyAllowedRoutePresent
            val entryCount = entries.count()
//        val lastVisibleEntryIsAllowed = allowedRoutes.contains(
//            visibleEntries[entryCount - 1].toRoute()
//        )
            val currentRoute = currentRoute(navController)

            if (currentRoute != null) {
                val currentEntryIsAllowed = allowedRoutes.contains(currentRoute)
                return entryCount == 1 && currentEntryIsAllowed //visible entries, not containing nav graph
            } else {
                return false
            }
        }
    }
}

