package com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases

import com.opappdevs.mindfulmoment.data.prefs.IntPreferences.NOTIFICATION_DAILY_TIME_MINUTES
import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import javax.inject.Inject

class GetNotificationTimeUseCaseImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : GetNotificationTimeUseCase {
    override operator fun invoke(): Int {
        return preferencesManager.get(NOTIFICATION_DAILY_TIME_MINUTES)
    }
}