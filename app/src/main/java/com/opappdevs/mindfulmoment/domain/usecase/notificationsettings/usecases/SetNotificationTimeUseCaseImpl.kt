package com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases

import com.opappdevs.mindfulmoment.data.prefs.IntPreferences.NOTIFICATION_DAILY_TIME_SECONDS
import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import com.opappdevs.mindfulmoment.ext.toSecondsOfDay
import java.time.LocalDateTime
import javax.inject.Inject

class SetNotificationTimeUseCaseImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : SetNotificationTimeUseCase {
    override operator fun invoke(time: LocalDateTime) {
        val seconds = time.toSecondsOfDay()
        preferencesManager.set(NOTIFICATION_DAILY_TIME_SECONDS, seconds)
    }
}