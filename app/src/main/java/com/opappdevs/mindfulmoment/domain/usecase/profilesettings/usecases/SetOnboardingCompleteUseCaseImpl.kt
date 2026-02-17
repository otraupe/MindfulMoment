package com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases

import com.opappdevs.mindfulmoment.data.prefs.BooleanPreferences.PROFILE_ONBOARDING_COMPLETE
import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import javax.inject.Inject

class SetOnboardingCompleteUseCaseImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : SetOnboardingCompleteUseCase {
    override operator fun invoke() {
        preferencesManager.set(PROFILE_ONBOARDING_COMPLETE, true)
    }
}