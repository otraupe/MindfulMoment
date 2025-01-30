package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager

import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.NotificationSettingsUseCases
import com.opappdevs.mindfulmoment.ui.view.base.pager.AnimatedPagerDots
import com.opappdevs.mindfulmoment.ui.view.base.pager.ControlledHorizontalPager
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages.INTRODUCTION
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages.NOTIFICATIONS
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages.PROFILE
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages.PageIntroduction
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages.PageNotifications
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages.PageProfile

@Composable
fun OnboardingPager(
    snackHostState: SnackbarHostState,
    navHostController: NavHostController,
    pagerVisible: MutableState<Boolean>,
//    advancePager: (OnboardingPages) -> Unit,
//    saveProfile: (String, Date) -> Unit,
    notificationSettingsActions: NotificationSettingsUseCases,
) {
    val pages = remember { OnboardingPages.entries }
    val startPage = remember { 0 } //future: allows advancing if partially complete
    val pagerState = rememberPagerState(initialPage = startPage) { pages.size }
    val pageDone = remember { mutableStateOf<OnboardingPages?>(null) }

    AnimatedVisibility(
        visible = pagerVisible.value,
        modifier = Modifier.fillMaxSize(),
        enter = fadeIn() + scaleIn(initialScale = .5f),
        exit = scaleOut(targetScale = .5f) + fadeOut(),
    ) {
        Column {
            ControlledHorizontalPager(
                pagerVisible = pagerVisible,
                pagerState = pagerState,
                startPage = startPage,
                pageDone = pageDone,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.9f),
                snackState = snackHostState,
                navController = navHostController,
                //TODO: clean-up
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
                    INTRODUCTION ->
                        PageIntroduction(
                            page = page,
                            pagerState = pagerState,
                            pageDone = { pageDone.value = page }
                        )
                    NOTIFICATIONS -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        PageNotifications(
                            page = page,
                            pagerState = pagerState,
                            pageDone = { pageDone.value = page },
                            notificationSettingsActions = notificationSettingsActions
                        )
                    }
                    PROFILE ->
                        PageProfile(
                            page = page,
                            pagerState = pagerState,
                            pageDone = { pageDone.value = page }
                        )
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