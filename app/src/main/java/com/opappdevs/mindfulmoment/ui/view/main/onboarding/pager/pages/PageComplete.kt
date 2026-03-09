package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
fun PageComplete(
    pageNumber: Int,
    page: OnboardingPages,
    pagerState: PagerState,
    setPageDone: () -> Unit,
    pagesDone: List<OnboardingPages>
) {
    Timber.d("PageComplete")

    var primaryButtonEnabled by rememberSaveable {
        mutableStateOf(!pagesDone.contains(page))
    }

    OnboardingPage(
        pageNumber = pageNumber,
        baseContent = page,
        pagerState = pagerState,
        infoButtonRes = R.string.ui_legal_privacy_title
    ) {
        MindfulButton(
            labelRes = R.string.ui_onboarding_pages_complete_button_primary,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.mindful_base_card_sub_spacing)),
            enabled = primaryButtonEnabled
        ) {
            primaryButtonEnabled = false
            setPageDone()
        }
    }
}

@ThemePreviews
@Composable
fun PreviewPageComplete() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        PageComplete(
            pageNumber = 0,
            page = OnboardingPages.COMPLETE,
            pagerState = rememberPagerState { 0 },
            setPageDone = { (OnboardingPages.COMPLETE) },
            pagesDone = listOf()
        )
    }
}