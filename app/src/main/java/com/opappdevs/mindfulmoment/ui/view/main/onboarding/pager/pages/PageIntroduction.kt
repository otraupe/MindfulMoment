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
import timber.log.Timber

@Composable
fun PageIntroduction(
    pageNumber: Int,
    page: OnboardingPages,
    pagerState: PagerState,
    setPageDone: () -> Unit,
    pagesDone: List<OnboardingPages>
) {
    Timber.d("PageIntroduction")
    OnboardingPage(
        pageNumber = pageNumber,
        baseContent = page,
        pagerState = pagerState,
    ) {
        var primaryButtonEnabled by rememberSaveable {
            mutableStateOf(!pagesDone.contains(page))
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MindfulButton(
                R.string.ui_onboarding_pages_introduction_button_primary,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.mindful_base_card_padding)
                ),
                enabled = primaryButtonEnabled
            ) {
                // check input conditions - none to check here
                // disable button; ensures pagedone.value can not go backwards
                primaryButtonEnabled = false
                // send done
                setPageDone()
            }
        }
    }
}

@ThemePreviews
@Composable
fun PreviewPageWelcome() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        PageIntroduction(
            pageNumber = 0,
            page = OnboardingPages.INTRODUCTION,
            pagerState = rememberPagerState { 0 },
            setPageDone = { (OnboardingPages.INTRODUCTION) },
            pagesDone = listOf()
        )
    }
}