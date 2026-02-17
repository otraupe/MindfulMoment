package com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases

import com.opappdevs.mindfulmoment.data.prefs.LongPreferences.PROFILE_BIRTHDAY_MILLIS
import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import javax.inject.Inject

class SetBirthdayMillisUseCaseImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : SetBirthdayMillisUseCase {
    override operator fun invoke(birthdayMillis: Long) {
        preferencesManager.set(PROFILE_BIRTHDAY_MILLIS, birthdayMillis)
    }
}