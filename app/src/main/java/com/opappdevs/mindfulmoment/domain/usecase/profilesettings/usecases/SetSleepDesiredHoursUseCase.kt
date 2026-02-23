package com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases

interface SetSleepDesiredHoursUseCase {
    operator fun invoke(sleepDesiredHours: Int)
}