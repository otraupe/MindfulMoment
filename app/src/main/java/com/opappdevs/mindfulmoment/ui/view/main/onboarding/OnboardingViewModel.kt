package com.opappdevs.mindfulmoment.ui.view.main.onboarding

import androidx.lifecycle.ViewModel
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.NotificationSettingsUseCases
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.ProfileSettingsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor (
    private val _notificationSettingsUseCases: NotificationSettingsUseCases,
    private val _profileSettingsUseCases: ProfileSettingsUseCases
): ViewModel() {
    //TODO: UI does not access use cases (switch fo MVI)

    // notification settings
    val notificationSettingsUseCases = _notificationSettingsUseCases // Expose grouped Use Cases

    // profile settings
    val profileSettingsUseCases = _profileSettingsUseCases
}