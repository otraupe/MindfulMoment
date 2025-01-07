package com.opappdevs.mindfulmoment.ui.view.main.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.opappdevs.mindfulmoment.ui.view.basic.pagers.AnimatedPagerDots
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pages.OnboardingPage
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pages.OnboardingPages

@Composable
fun OnboardingPager(
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val pagerState = rememberPagerState { OnboardingPages.entries.size }
    val pages = rememberSaveable { OnboardingPages.entries }

    Column(
        modifier = modifier
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(.9f),
//        contentPadding =,
//        pageSize =,
//        beyondViewportPageCount = 0,
//        pageSpacing =,
//        verticalAlignment =,
//        flingBehavior =,
//        userScrollEnabled = false,
//        reverseLayout = false,
//        key = { -> },
//        pageNestedScrollConnection =,
//        snapPosition =,
        ) {
                page -> OnboardingPage(pages.get(page))
        }
        AnimatedPagerDots(
            count = pages.size,
            pagerState = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(.1f)
                .padding(bottom = 24.dp)
        )
    }
}