package com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases

import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import com.opappdevs.mindfulmoment.data.prefs.StringPreferences.PROFILE_NAME
import javax.inject.Inject

class GetProfileNameUseCaseImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : GetProfileNameUseCase {
    override operator fun invoke(): String {
        return preferencesManager.get(PROFILE_NAME)
    }
}