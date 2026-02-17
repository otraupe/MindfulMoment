package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.opappdevs.mindfulmoment.R

//TODO: add primary button label
enum class OnboardingPages(
    val iconVector: ImageVector,
    val iconContentDescriptionRes: Int,
    val titleRes: Int,
    val bodyRes: Int,
    val infoRes: Int? = null,
) {
    INTRODUCTION(
        Icons.Filled.Favorite,
        R.string.ui_onboarding_pages_introduction_icon_cd,
        R.string.ui_onboarding_pages_introduction_title,
        R.string.ui_onboarding_pages_introduction_body,
        R.string.ui_onboarding_pages_introduction_body_sub
    ),
    NOTIFICATIONS(
        Icons.Filled.Notifications,
        R.string.ui_onboarding_pages_notifications_icon_cd,
        R.string.ui_onboarding_pages_notifications_title,
        R.string.ui_onboarding_pages_notifications_body,
        R.string.ui_onboarding_pages_notifications_body_sub
    ),
    PROFILE(
        Icons.Filled.Person,
        R.string.ui_onboarding_pages_profile_icon_cd,
        R.string.ui_onboarding_pages_profile_title,
        R.string.ui_onboarding_pages_profile_body,
        R.string.ui_onboarding_pages_profile_body_sub
    );

    fun isFirstPage() = ordinal == 0
    fun isLastPage() = ordinal == OnboardingPages.entries.size - 1
}