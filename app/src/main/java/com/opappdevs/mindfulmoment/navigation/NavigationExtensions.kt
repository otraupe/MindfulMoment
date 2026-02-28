package com.opappdevs.mindfulmoment.navigation

import androidx.navigation.NavHostController

/**
 * Navigates to a route only if it's not the current destination.
 * This prevents creating multiple copies of the same destination on the back stack.
 *
 * @param route The destination route to navigate to.
 */
fun NavHostController.navigateIfNew(route: String, popUpTo: Destination? = null) {
    if (this.currentBackStackEntry?.destination?.route != route) {
        if (popUpTo != null) {
            this.navigate(route) {
                popUpTo(popUpTo.route) {
                    inclusive = false
                }
            }
        } else {
            this.navigate(route)
        }
    }
}
