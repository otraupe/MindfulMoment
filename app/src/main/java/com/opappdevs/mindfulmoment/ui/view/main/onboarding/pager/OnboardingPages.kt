package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager

import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages.PageNotifications

enum class OnboardingPages(
    val iconRes: Int,
    val titleRes: Int,
    val bodyRes: Int,
    val subBodyRes: Int?,
    val buttonPrimarySpec: OnboardingButtonSpec,
    val buttonSecondarySpec: OnboardingButtonSpec?,
    val viewModel: OnboardingPageViewModel
) {
    //enum order is order of presentation
    INTRODUCTION (),
    //    USER_CREATION ("Create a User", { PageNotifications() }),
    NOTIFICATIONS ("Enable Notifications?", { PageNotifications() }),

    //FINAL - "all set, ready for your first Mindful Moment?"
}