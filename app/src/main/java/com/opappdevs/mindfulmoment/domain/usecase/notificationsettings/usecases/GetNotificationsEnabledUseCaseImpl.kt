package com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases

import com.opappdevs.mindfulmoment.data.prefs.BooleanPreferences.NOTIFICATIONS_ENABLED
import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import javax.inject.Inject

class GetNotificationsEnabledUseCaseImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : GetNotificationsEnabledUseCase {
    override operator fun invoke(): Boolean {
        return preferencesManager.get(NOTIFICATIONS_ENABLED)
    }
}