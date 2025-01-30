package com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.usecases

import java.time.LocalDateTime

interface SetNotificationTimeUseCase {
    operator fun invoke(time: LocalDateTime)
}