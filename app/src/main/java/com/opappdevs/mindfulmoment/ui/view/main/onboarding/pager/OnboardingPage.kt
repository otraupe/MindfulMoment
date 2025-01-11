package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.opappdevs.mindfulmoment.ui.view.base.pager.Page
import kotlin.math.absoluteValue

@Composable
fun OnboardingPage(
    id: Int,
    stringContent: OnboardingPages,
    pagerState: PagerState
) {
    val offsetDerivedState by remember {
        derivedStateOf {
            val absOffset = pagerState.getOffsetDistanceInPages(stringContent.ordinal)
                .absoluteValue
                Pair(1f - absOffset, 1f - absOffset / 8)
        }
    }
    Page(
        state = offsetDerivedState,
        title = stringContent.title
    ) {
        stringContent.body
    }
}