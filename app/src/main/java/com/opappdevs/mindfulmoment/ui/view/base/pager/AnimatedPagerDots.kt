package com.opappdevs.mindfulmoment.ui.view.base.pager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages

@Composable
fun AnimatedPagerDots(
    count: Int,
    currentlyAddedPages: MutableState<List<OnboardingPages>>,
    pagerState: PagerState,
    modifier: Modifier = Modifier.fillMaxWidth().wrapContentHeight()
) {
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (page in 0..<count) {
            PagerDot(
                pagerState = pagerState,
                page = page,
                currentlyAddedPages = currentlyAddedPages
            )
        }
    }
}