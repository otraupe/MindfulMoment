package com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases

import com.opappdevs.mindfulmoment.data.prefs.IntPreferences.NOTIFICATION_DAILY_TIME_MINUTES
import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import javax.inject.Inject

class SetNotificationTimeUseCaseImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : SetNotificationTimeUseCase {
    override operator fun invoke(minutesOfDay: Int) {
        preferencesManager.set(NOTIFICATION_DAILY_TIME_MINUTES, minutesOfDay)
    }
}