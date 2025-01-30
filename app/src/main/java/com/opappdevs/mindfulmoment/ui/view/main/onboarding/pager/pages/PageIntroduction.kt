package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPage
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages

@Composable
fun PageIntroduction(
    page: OnboardingPages,
    pagerState: PagerState,
    pageDone: (OnboardingPages) -> Unit,
) {
    OnboardingPage(
        baseContent = page,
        pagerState = pagerState
    ) {
        var buttonEnabled by rememberSaveable { mutableStateOf(true) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MindfulButton(
                R.string.ui_base_button_ok,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.mindful_base_card_padding)
                ),
                enabled = buttonEnabled
            ) {
                // check input conditions
                // disable button
                buttonEnabled = false
                // send done
                pageDone(page)
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
            pageDone = { (OnboardingPages.INTRODUCTION) }
        )
    }
}