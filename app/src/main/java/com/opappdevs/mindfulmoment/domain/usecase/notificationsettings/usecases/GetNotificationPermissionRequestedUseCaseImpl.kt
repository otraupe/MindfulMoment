package com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases

import com.opappdevs.mindfulmoment.data.prefs.BooleanPreferences.NOTIFICATIONS_PERMISSION_REQUESTED
import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import javax.inject.Inject

class GetNotificationPermissionRequestedUseCaseImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : GetNotificationPermissionRequestedUseCase {
    override operator fun invoke(): Boolean {
        return preferencesManager.get(NOTIFICATIONS_PERMISSION_REQUESTED)
    }
}