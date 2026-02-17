package com.opappdevs.mindfulmoment.data.prefs

object IntPreferences {
    // 0 = indefinitely
    val DATA_RETENTION_PERIOD_DAYS = Preference.IntPreference("data.retention.period", 0)

    // negative: not set
    val NOTIFICATION_DAILY_TIME_MINUTES = Preference.IntPreference("notifications.daily.time", -1)
}