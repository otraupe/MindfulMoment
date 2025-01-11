package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager

class OnboardingButtonSpec(
    val type: Type,
    val shape: Shape,
    val function: Function,
    val label: String
) {
    enum class Type() {
        BUTTON,
        BUTTON_TEXT,
    }
    enum class Shape() {
        CORNERED,
        ROUND,
    }
    enum class Function() {
        BUTTON_PRIMARY_FUNCTION,
        BUTTON_SECONDARY_FUNCTION,
    }
}