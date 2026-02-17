package com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases

interface SetBirthdayMillisUseCase {
    operator fun invoke(birthdayMillis: Long)
}