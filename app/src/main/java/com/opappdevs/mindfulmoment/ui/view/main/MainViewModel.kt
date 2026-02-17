package com.opappdevs.mindfulmoment.ui.view.main

import androidx.lifecycle.ViewModel
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.ProfileSettingsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val _profileSettingsUseCases: ProfileSettingsUseCases
): ViewModel() {
    fun isOnboardingComplete(): Boolean {
        return _profileSettingsUseCases.getOnboardingCompleteUseCase()
    }
}