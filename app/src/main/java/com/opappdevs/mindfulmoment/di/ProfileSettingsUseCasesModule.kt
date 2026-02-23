package com.opappdevs.mindfulmoment.di

import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.ProfileSettingsUseCases
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.GetBirthdayMillisUseCase
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.GetBirthdayMillisUseCaseImpl
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.GetOnboardingCompleteUseCase
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.GetOnboardingCompleteUseCaseImpl
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.GetProfileNameUseCase
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.GetProfileNameUseCaseImpl
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.GetSleepDesiredHoursUseCase
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.GetSleepDesiredHoursUseCaseImpl
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.SetBirthdayMillisUseCase
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.SetBirthdayMillisUseCaseImpl
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.SetOnboardingCompleteUseCase
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.SetOnboardingCompleteUseCaseImpl
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.SetProfileNameUseCase
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.SetProfileNameUseCaseImpl
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.SetSleepDesiredHoursUseCase
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases.SetSleepDesiredHoursUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object ProfileSettingsUseCasesModule {

    @Provides
    fun provideGetProfileNameUseCase(
        preferencesManager: PreferencesManager
    ): GetProfileNameUseCase {
        return GetProfileNameUseCaseImpl(preferencesManager)
    }

    @Provides
    fun provideSetProfileNameUseCase(
        preferencesManager: PreferencesManager
    ): SetProfileNameUseCase {
        return SetProfileNameUseCaseImpl(preferencesManager)
    }

    @Provides
    fun provideGetBirthdayMillisUseCase(
        preferencesManager: PreferencesManager
    ): GetBirthdayMillisUseCase {
        return GetBirthdayMillisUseCaseImpl(preferencesManager)
    }

    @Provides
    fun provideSetBirthdayMillisUseCase(
        preferencesManager: PreferencesManager
    ): SetBirthdayMillisUseCase {
        return SetBirthdayMillisUseCaseImpl(preferencesManager)
    }

    @Provides
    fun provideGetOnboardingCompleteUseCase(
        preferencesManager: PreferencesManager
    ): GetOnboardingCompleteUseCase {
        return GetOnboardingCompleteUseCaseImpl(preferencesManager)
    }

    @Provides
    fun provideSetOnboardingCompleteUseCase(
        preferencesManager: PreferencesManager
    ): SetOnboardingCompleteUseCase {
        return SetOnboardingCompleteUseCaseImpl(preferencesManager)
    }

    @Provides
    fun provideGetSleepDesiredHoursUseCase(
        preferencesManager: PreferencesManager
    ): GetSleepDesiredHoursUseCase {
        return GetSleepDesiredHoursUseCaseImpl(preferencesManager)
    }

    @Provides
    fun provideSetSleepDesiredHoursUseCase(
        preferencesManager: PreferencesManager
    ): SetSleepDesiredHoursUseCase {
        return SetSleepDesiredHoursUseCaseImpl(preferencesManager)
    }

    @Provides
    fun provideProfileSettingsUseCases(
        getProfileName: GetProfileNameUseCase,
        setProfileName: SetProfileNameUseCase,
        getBirthdayMillis: GetBirthdayMillisUseCase,
        setBirthdayMillis: SetBirthdayMillisUseCase,
        getOnboardingComplete: GetOnboardingCompleteUseCase,
        setOnboardingComplete: SetOnboardingCompleteUseCase,
        getSleepDesiredHours: GetSleepDesiredHoursUseCase,
        setSleepDesiredHours: SetSleepDesiredHoursUseCase,
    ): ProfileSettingsUseCases {
        return ProfileSettingsUseCases(
            getProfileName,
            setProfileName,
            getBirthdayMillis,
            setBirthdayMillis,
            getOnboardingComplete,
            setOnboardingComplete,
            getSleepDesiredHours,
            setSleepDesiredHours
        )
    }
}