package com.opappdevs.mindfulmoment.navigation

/**
 * Defines the available screens/composables for navigation.
 * */
sealed class Screens(val route: String) {
    data object Onboarding: Screens("onboarding")
    data object Home: Screens("home")
    data object Settings: Screens("settings")
    data object Profile: Screens("profile")
}