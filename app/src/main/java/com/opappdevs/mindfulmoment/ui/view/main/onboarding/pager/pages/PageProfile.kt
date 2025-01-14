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
import java.util.Date

@Composable
fun PageProfile(
    page: OnboardingPages,
    pagerState: PagerState,
    advancePager: (OnboardingPages) -> Unit,
    saveProfile: (String, Date) -> Unit,
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
                string = "Speichern",
                modifier = Modifier.padding(
                    top = 48.dp
                )
            ) {
                advancePager(page)
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
            advancePager = {},
            saveProfile = { _, _ -> }
        )
    }
}