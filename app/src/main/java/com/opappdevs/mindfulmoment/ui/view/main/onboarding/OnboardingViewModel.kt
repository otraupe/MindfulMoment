package com.opappdevs.mindfulmoment.ui.view.main.onboarding

import androidx.lifecycle.ViewModel
import com.opappdevs.mindfulmoment.domain.notification.AlarmScheduler
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.NotificationSettingsUseCases
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.ProfileSettingsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor (
    val notificationSettingsUseCases: NotificationSettingsUseCases,
    val profileSettingsUseCases: ProfileSettingsUseCases,
    private val _alarmScheduler: AlarmScheduler
): ViewModel() {
    //TODO: UI does not access use cases (switch to MVI)

    // notification settings
    //val notificationSettingsUseCases = notificationSettingsUseCases // Expose grouped Use Cases

    // profile settings
    //val profileSettingsUseCases = profileSettingsUseCases

    fun canScheduleExactAlarms(): Boolean {
        return _alarmScheduler.canScheduleExactAlarms()
    }
}