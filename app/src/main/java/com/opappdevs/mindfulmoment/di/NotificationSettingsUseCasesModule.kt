package com.opappdevs.mindfulmoment.di

import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.NotificationSettingsUseCases
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases.GetNotificationPermissionRequestedUseCase
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases.GetNotificationPermissionRequestedUseCaseImpl
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases.GetNotificationTimeUseCase
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases.GetNotificationTimeUseCaseImpl
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases.GetNotificationsEnabledUseCase
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases.GetNotificationsEnabledUseCaseImpl
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases.SetNotificationPermissionRequestedUseCase
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases.SetNotificationPermissionRequestedUseCaseImpl
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases.SetNotificationTimeUseCase
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases.SetNotificationTimeUseCaseImpl
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases.SetNotificationsEnabledUseCase
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases.SetNotificationsEnabledUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object NotificationSettingsUseCasesModule {

    @Provides
    fun provideGetNotificationPermissionRequestedUseCase(
        preferencesManager: PreferencesManager
    ): GetNotificationPermissionRequestedUseCase {
        return GetNotificationPermissionRequestedUseCaseImpl(preferencesManager)
    }

    @Provides
    fun provideSetNotificationPermissionRequestedUseCase(
        preferencesManager: PreferencesManager
    ): SetNotificationPermissionRequestedUseCase {
        return SetNotificationPermissionRequestedUseCaseImpl(preferencesManager)
    }

    @Provides
    fun provideGetNotificationPermissionEnabledUseCase(
        preferencesManager: PreferencesManager
    ): GetNotificationsEnabledUseCase {
        return GetNotificationsEnabledUseCaseImpl(preferencesManager)
    }

    @Provides
    fun provideSetNotificationPermissionEnabledUseCase(
        preferencesManager: PreferencesManager
    ): SetNotificationsEnabledUseCase {
        return SetNotificationsEnabledUseCaseImpl(preferencesManager)
    }

    @Provides
    fun provideGetNotificationTimeUseCase(
        preferencesManager: PreferencesManager
    ): GetNotificationTimeUseCase {
        return GetNotificationTimeUseCaseImpl(preferencesManager)
    }

    @Provides
    fun provideSetNotificationTimeUseCase(
        preferencesManager: PreferencesManager
    ): SetNotificationTimeUseCase {
        return SetNotificationTimeUseCaseImpl(preferencesManager)
    }

    @Provides
    fun provideNotificationSettingsUseCases(
        getNotificationPermissionRequested: GetNotificationPermissionRequestedUseCase,
        setNotificationPermissionRequested: SetNotificationPermissionRequestedUseCase,
        getNotificationsEnabled: GetNotificationsEnabledUseCase,
        setNotificationsEnabled: SetNotificationsEnabledUseCase,
        getNotificationTime: GetNotificationTimeUseCase,
        setNotificationTime: SetNotificationTimeUseCase
    ): NotificationSettingsUseCases {
        return NotificationSettingsUseCases(
            getNotificationPermissionRequested,
            setNotificationPermissionRequested,
            getNotificationsEnabled,
            setNotificationsEnabled,
            getNotificationTime,
            setNotificationTime
        )
    }
}