package com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases

import com.opappdevs.mindfulmoment.data.prefs.LongPreferences.PROFILE_BIRTHDAY_MILLIS
import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import javax.inject.Inject

class GetBirthdayMillisUseCaseImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : GetBirthdayMillisUseCase {
    override operator fun invoke(): Long {
        return preferencesManager.get(PROFILE_BIRTHDAY_MILLIS)
    }
}