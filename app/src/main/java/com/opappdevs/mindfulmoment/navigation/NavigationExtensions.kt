package com.opappdevs.mindfulmoment.navigation

import androidx.navigation.NavHostController

/**
 * Navigates to a route only if it's not the current destination.
 * This prevents creating multiple copies of the same destination on the back stack.
 *
 * @param route The destination route to navigate to.
 */
fun NavHostController.navigateIfNew(route: String) {
    if (this.currentBackStackEntry?.destination?.route != route) {
        this.navigate(route)
    }
}
