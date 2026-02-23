package com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases

import com.opappdevs.mindfulmoment.data.prefs.IntPreferences.PROFILE_SLEEP_DESIRED_HOURS
import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import javax.inject.Inject

class GetSleepDesiredHoursUseCaseImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : GetSleepDesiredHoursUseCase {
    override operator fun invoke(): Int {
        return preferencesManager.get(PROFILE_SLEEP_DESIRED_HOURS)
    }
}