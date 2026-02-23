package com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases

import com.opappdevs.mindfulmoment.data.prefs.IntPreferences.PROFILE_SLEEP_DESIRED_HOURS
import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import javax.inject.Inject

class SetSleepDesiredHoursUseCaseImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : SetSleepDesiredHoursUseCase {
    override operator fun invoke(sleepDesiredHours: Int) {
        preferencesManager.set(PROFILE_SLEEP_DESIRED_HOURS, sleepDesiredHours)
    }
}