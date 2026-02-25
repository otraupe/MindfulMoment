package com.opappdevs.mindfulmoment.navigation

/**
 * Defines the available screens/composables for navigation.
 * */
sealed class Destinations(val route: String) {
    data object Onboarding: Destinations("onboarding")
    data object Home: Destinations("home")
    data object Settings: Destinations("settings")
    data object Profile: Destinations("profile")
    data object Imprint: Destinations("imprint")
    data object PrivacyPolicy: Destinations("privacy_policy")
}