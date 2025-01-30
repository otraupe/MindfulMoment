package com.opappdevs.mindfulmoment.domain.usecase.notificationsettings

import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases.GetNotificationPermissionRequestedUseCase
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases.GetNotificationTimeUseCase
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases.GetNotificationsEnabledUseCase
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases.SetNotificationPermissionRequestedUseCase
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases.SetNotificationTimeUseCase
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases.SetNotificationsEnabledUseCase
import javax.inject.Inject

data class NotificationSettingsUseCases @Inject constructor(
    val getNotificationPermissionRequested: GetNotificationPermissionRequestedUseCase,
    val setNotificationPermissionRequested: SetNotificationPermissionRequestedUseCase,
    val getNotificationsEnabled: GetNotificationsEnabledUseCase,
    val setNotificationsEnabled: SetNotificationsEnabledUseCase,
    val getNotificationTime: GetNotificationTimeUseCase,
    val setNotificationTime: SetNotificationTimeUseCase
)