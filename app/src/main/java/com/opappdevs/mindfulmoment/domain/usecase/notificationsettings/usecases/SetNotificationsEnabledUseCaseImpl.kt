package com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases

import com.opappdevs.mindfulmoment.data.prefs.BooleanPreferences.NOTIFICATIONS_ENABLED
import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import javax.inject.Inject

class SetNotificationsEnabledUseCaseImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : SetNotificationsEnabledUseCase {
    override operator fun invoke() {
        preferencesManager.set(NOTIFICATIONS_ENABLED, true)
    }
}