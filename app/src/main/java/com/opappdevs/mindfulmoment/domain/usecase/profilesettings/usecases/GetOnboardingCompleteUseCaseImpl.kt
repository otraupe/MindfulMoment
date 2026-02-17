package com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases

import com.opappdevs.mindfulmoment.data.prefs.BooleanPreferences.PROFILE_ONBOARDING_COMPLETE
import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import javax.inject.Inject

class GetOnboardingCompleteUseCaseImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : GetOnboardingCompleteUseCase {
    override operator fun invoke(): Boolean {
        return preferencesManager.get(PROFILE_ONBOARDING_COMPLETE)
    }
}