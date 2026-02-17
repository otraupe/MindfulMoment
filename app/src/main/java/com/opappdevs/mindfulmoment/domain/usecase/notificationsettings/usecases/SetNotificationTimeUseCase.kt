package com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases

interface SetNotificationTimeUseCase {
    operator fun invoke(minutesOfDay: Int)
}