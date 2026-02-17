package com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases

import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import com.opappdevs.mindfulmoment.data.prefs.StringPreferences.PROFILE_NAME
import javax.inject.Inject

class SetProfileNameUseCaseImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : SetProfileNameUseCase {
    override operator fun invoke(profileName: String) {
        preferencesManager.set(PROFILE_NAME, profileName)
    }
}