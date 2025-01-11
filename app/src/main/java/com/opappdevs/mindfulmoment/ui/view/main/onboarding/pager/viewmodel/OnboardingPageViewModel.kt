package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.viewmodel

import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingButtonSpec
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingButtonSpec.Function.*

interface OnboardingPageViewModel {

    fun execute(function: OnboardingButtonSpec.Function) {
        when (function) {
            BUTTON_PRIMARY_FUNCTION -> buttonPrimaryFunction()
            BUTTON_SECONDARY_FUNCTION -> buttonSecondaryFunction()
        }
    }
    abstract fun buttonPrimaryFunction(): Unit
    abstract fun buttonSecondaryFunction(): Unit
}