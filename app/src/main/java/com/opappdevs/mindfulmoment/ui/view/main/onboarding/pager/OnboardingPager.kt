package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.opappdevs.mindfulmoment.ui.view.base.pager.AnimatedPagerDots
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages.PageIntroduction
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages.*
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages.PageNotifications
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages.PageProfile
import java.util.Date

@Composable
fun OnboardingPager(
    pagerState: PagerState,
    pagerVisible: MutableState<Boolean>,
    advancePager: (OnboardingPages) -> Unit,
    saveProfile: (String, Date) -> Unit
) {
    val pages = rememberSaveable { OnboardingPages.entries }

    AnimatedVisibility(
        visible = pagerVisible.value,
        modifier = Modifier.fillMaxSize(),
        enter = fadeIn() + scaleIn(initialScale = .5f),
        exit = scaleOut(targetScale = .5f) + fadeOut(),
    ) {
        Column {
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
            ) { pageNumber ->
                val page = OnboardingPages.entries[pageNumber]
                when(page) {
                    INTRODUCTION -> PageIntroduction(INTRODUCTION, pagerState, advancePager)
                    NOTIFICATIONS -> PageNotifications(NOTIFICATIONS, pagerState, advancePager)
                    PROFILE -> PageProfile(PROFILE, pagerState, advancePager, saveProfile)
                }
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
}