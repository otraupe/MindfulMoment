package com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases

import com.opappdevs.mindfulmoment.data.prefs.BooleanPreferences.NOTIFICATIONS_PERMISSION_REQUESTED
import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import javax.inject.Inject

class SetNotificationPermissionRequestedUseCaseImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : SetNotificationPermissionRequestedUseCase {
    override operator fun invoke() {
        preferencesManager.set(NOTIFICATIONS_PERMISSION_REQUESTED, true)
    }
}