package com.opappdevs.mindfulmoment.data.prefs

object BooleanPreferences {
    // permissions
    val NOTIFICATIONS_PERMISSION_REQUESTED = Preference.BooleanPreference("permission.notifications.requested", false)

    // features
    val NOTIFICATIONS_ENABLED = Preference.BooleanPreference("feature.notifications.enabled", false)
}