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
//    private val _pagerPageDone: MutableStateFlow<OnboardingPages?> = MutableStateFlow(null)
//    val pagerPageDone: StateFlow<OnboardingPages?> = _pagerPageDone
//
//    //TODO: we don't need the viewmodel for this
//    fun advancePager(source: OnboardingPages) {
//        Timber.d("Advance pager from page $source")
//        _pagerPageDone.value = source
//    }

    // notification settings
    val notificationSettingsUseCases = _notificationSettingsUseCases // Expose grouped Use Cases

    // profile settings
    val profileSettingsUseCases = _profileSettingsUseCases


//    // user settings
//    val userSettingsActions = userSettingsUseCases // Expose grouped Use Cases
//
//    // user management
//    fun getLastUser() {
//        // TODO: see whether a user was already created, but onboarding not completed
//    }
//
//    fun saveProfile(userName: String, birthDate: Date) {
////        userRepository.setCurrentUser() //manager
//    }
}