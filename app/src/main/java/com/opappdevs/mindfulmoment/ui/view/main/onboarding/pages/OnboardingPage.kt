package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pages

import androidx.compose.runtime.Composable
import com.opappdevs.mindfulmoment.ui.view.basic.pagers.PageView

@Composable
fun OnboardingPage(content: OnboardingPages) {
    PageView(content.title)
}