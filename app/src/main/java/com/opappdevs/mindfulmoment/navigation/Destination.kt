package com.opappdevs.mindfulmoment.navigation

/**
 * Defines the available screens/composables for navigation.
 * */
sealed class Destination(val route: String) {
    data object Onboarding: Destination("onboarding")
    data object Home: Destination("home")
    data object Settings: Destination("settings")
    data object Profile: Destination("profile")
    data object Imprint: Destination("imprint")
    data object PrivacyPolicy: Destination("privacy_policy")
}