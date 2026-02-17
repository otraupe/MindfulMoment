package com.opappdevs.mindfulmoment.domain.usecase.profilesettings

import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.GetBirthdayMillisUseCase
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.GetOnboardingCompleteUseCase
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.GetProfileNameUseCase
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.SetBirthdayMillisUseCase
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.SetOnboardingCompleteUseCase
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.SetProfileNameUseCase
import javax.inject.Inject

data class ProfileSettingsUseCases @Inject constructor(
    val getProfileNameUseCase: GetProfileNameUseCase,
    val setProfileNameUseCase: SetProfileNameUseCase,
    val getBirthdayMillisUseCase: GetBirthdayMillisUseCase,
    val setBirthdayMillisUseCase: SetBirthdayMillisUseCase,
    val getOnboardingCompleteUseCase: GetOnboardingCompleteUseCase,
    val setOnboardingCompleteUseCase: SetOnboardingCompleteUseCase,
)