package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulTextButton
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPage
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages

@Composable
fun PageNotifications(
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
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MindfulTextButton(
                string = "Nicht jetzt",
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.mindful_base_card_padding)
                )
            ) {
                advancePager(page)
            }
            MindfulButton(
                string = "Gerne",
                modifier = Modifier.padding(top = 4.dp)
            ) {
                advancePager(page)
            }
        }
    }
}

@ThemePreviews
@Composable
fun PreviewPageNotifications() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        PageNotifications(
            page = OnboardingPages.NOTIFICATIONS,
            pagerState = rememberPagerState { 0 },
            advancePager = {}
        )
    }
}