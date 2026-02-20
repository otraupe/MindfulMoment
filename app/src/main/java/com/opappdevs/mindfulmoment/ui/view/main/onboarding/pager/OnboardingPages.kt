package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager

import com.opappdevs.mindfulmoment.R

enum class OnboardingPages(
    val iconRes: Int,
    val iconContentDescriptionRes: Int,
    val titleRes: Int,
    val bodyRes: Int,
    val infoRes: Int? = null,
) {
    INTRODUCTION(
        R.drawable.ic_favorite_24px_rounded,
        R.string.ui_onboarding_pages_introduction_icon_cd,
        R.string.ui_onboarding_pages_introduction_title,
        R.string.ui_onboarding_pages_introduction_body,
        R.string.ui_onboarding_pages_introduction_body_sub
    ),
    NOTIFICATIONS(
        R.drawable.ic_notifications_24px_rounded,
        R.string.ui_onboarding_pages_notifications_icon_cd,
        R.string.ui_onboarding_pages_notifications_title,
        R.string.ui_onboarding_pages_notifications_body,
        R.string.ui_onboarding_pages_notifications_body_sub
    ),
    ALARMS(
        R.drawable.ic_alarm_24px_rounded,
        R.string.ui_onboarding_pages_alarms_icon_cd,
        R.string.ui_onboarding_pages_alarms_title,
        R.string.ui_onboarding_pages_alarms_body,
        R.string.ui_onboarding_pages_alarms_body_sub
    ),
    PROFILE(
        R.drawable.ic_person_24px_rounded,
        R.string.ui_onboarding_pages_profile_icon_cd,
        R.string.ui_onboarding_pages_profile_title,
        R.string.ui_onboarding_pages_profile_body,
        R.string.ui_onboarding_pages_profile_body_sub
    );

    fun isFirstPage() = ordinal == 0
    fun isLastPage() = ordinal == OnboardingPages.entries.size - 1
}