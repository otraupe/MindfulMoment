package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.OutlinedTextField
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
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPage
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages

@Composable
fun PageProfile(
    page: OnboardingPages,
    pagerState: PagerState,
    setPageDone: (OnboardingPages) -> Unit
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
            //TODO: fields vertically centered in the available space?
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Name") },
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.mindful_base_card_padding)
                )
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Geburtsdatum") },
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.mindful_base_card_padding)
                )
            )
            MindfulButton(
                labelRes = R.string.ui_onboarding_pages_profile_button_primary ,
                modifier = Modifier.padding(
                    top = 48.dp
                )
            ) {
                setPageDone(page)
            }
        }
    }
}

@ThemePreviews
@Composable
fun PreviewPageProfile() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        PageProfile (
            page = OnboardingPages.PROFILE,
            pagerState = rememberPagerState { 0 },
            setPageDone = { (OnboardingPages.PROFILE) }
        )
    }
}