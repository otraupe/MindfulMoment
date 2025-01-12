package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPage
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages

@Composable
fun PageIntroduction(
    page: OnboardingPages,
    pagerState: PagerState,
    advancePager: (OnboardingPages) -> Unit
) {
    OnboardingPage(
        baseContent = page,
        pagerState = pagerState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
            )
            //Buttons
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //TODO: buttons feste breite
                MindfulButton("OK") {
                    advancePager(page)
                }
            }
        }
    }
}

@ThemePreviews
@Composable
fun PreviewPageWelcome() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        PageIntroduction(
            page = OnboardingPages.INTRODUCTION,
            pagerState = rememberPagerState { 0 },
            advancePager = {}
        )
    }
}