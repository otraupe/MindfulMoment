package com.opappdevs.mindfulmoment.domain.usecase.profilesettings.usecases

interface SetProfileNameUseCase {
    operator fun invoke(profileName: String)
}