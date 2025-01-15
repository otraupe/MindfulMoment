package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager

import androidx.lifecycle.ViewModel
import com.opappdevs.mindfulmoment.data.db.model.user.UserDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor (
    private val userRepository: UserDbRepository
): ViewModel() {
    private val _pagerPageDone: MutableStateFlow<OnboardingPages?> = MutableStateFlow(null)
    val pagerPageDone: StateFlow<OnboardingPages?> = _pagerPageDone

    fun advancePager(source: OnboardingPages) {
        _pagerPageDone.value = source
    }

    fun getLastUser() {
        // TODO
    }

    fun saveProfile(userName: String, birthDate: Date) {
//        userRepository.setCurrentUser()
    }
}