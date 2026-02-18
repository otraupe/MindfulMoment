package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.NotificationSettingsUseCases
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.ProfileSettingsUseCases
import com.opappdevs.mindfulmoment.navigation.Destinations
import com.opappdevs.mindfulmoment.ui.view.base.pager.AnimatedPagerDots
import com.opappdevs.mindfulmoment.ui.view.base.pager.MindfulHorizontalPager
import com.opappdevs.mindfulmoment.ui.view.base.pager.PagerScrollAnimationSpec
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages.INTRODUCTION
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages.NOTIFICATIONS
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages.PROFILE
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages.PageIntroduction
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages.PageNotifications
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages.PageProfile
import timber.log.Timber

@Composable
fun OnboardingPager(
    snackHostState: SnackbarHostState,
    navHostController: NavHostController,
    pagerTransitionState: MutableTransitionState<Boolean>,
    notificationSettingsUseCases: NotificationSettingsUseCases,
    profileSettingsUseCases: ProfileSettingsUseCases
) {
    val pages = rememberSaveable { mutableStateOf(listOf(
        OnboardingPages.entries[0],
    )) }
    val pagerState = rememberPagerState { pages.value.size }
    val pageDone = remember { mutableStateOf<OnboardingPages?>(null) }

    //ensure pageDone cannot go backwards
    fun updatePageDoneValue(page: OnboardingPages) {
        if (page.ordinal > (pageDone.value?.ordinal ?: -1)) {
            pageDone.value = page
        }
    }

    LaunchedEffect(pageDone.value) {
        Timber.d("LaunchedEffect pageDone.value ${pageDone.value}")
        val pdv = pageDone.value
        if (pdv != null) {
            if (!pdv.isLastPage()) {
                //add next page
                pages.value += OnboardingPages.entries[pdv.ordinal + 1]

                if (pdv.isFirstPage()) {
                    snackHostState.showSnackbar(
                        message = "Wischen zum Zurückgehen",
                        actionLabel = "OK",
                        duration = SnackbarDuration.Short
                    )
                }
            } else {
                pagerTransitionState.targetState = false
                profileSettingsUseCases.setOnboardingCompleteUseCase()
                navHostController.navigate(Destinations.Home.route) {
                    popUpTo(Destinations.Onboarding.route) {
                        inclusive = true
                    }
                }
            }
        }
    }

    //TODO: looks weird if we wait for the pager fade-out to complete
    //This effect will run when the animation finishes.
//    LaunchedEffect(pagerTransitionState.isIdle, pagerTransitionState.currentState) {
//        // We navigate only when the animation is finished (isIdle) AND
//        // the target state was false (it has just finished disappearing).
//        // Additionally we ensure this does not happen right at the start.
//        if (pagerTransitionState.isIdle && !pagerTransitionState.currentState && pages.value.size > 1) {
//            navHostController.navigate(Destinations.Home.route) {
//                popUpTo(Destinations.Onboarding.route) {
//                    inclusive = true
//                }
//            }
//        }
//    }

    //if not in a separate LaunchedEffect, we have a race condition where this won't fire
    LaunchedEffect(pages.value) {
        Timber.d("LaunchedEffect pages increased to ${pages.value.size}")
        if (pages.value.size > 1) {
            pagerState.animateScrollToPage(
                page = pagerState.currentPage + 1,
                animationSpec = PagerScrollAnimationSpec.deceleratingScrollAnimationSpec()
            )
        }
    }

    AnimatedVisibility(
        visibleState = pagerTransitionState,
        modifier = Modifier.fillMaxSize(),
        enter = fadeIn() + scaleIn(initialScale = .5f),
        exit = scaleOut(targetScale = .5f)// + fadeOut(), //onboarding already fades out
    ) {
        Column {
            MindfulHorizontalPager(
                pagerState = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.9f)
            ) { pageNumber ->
                when(val page = OnboardingPages.entries[pageNumber]) {
                    INTRODUCTION ->
                        PageIntroduction(
                            page = page,
                            pagerState = pagerState,
                            setPageDone = { updatePageDoneValue(page) }
                        )
                    PROFILE ->
                        PageProfile(
                            page = page,
                            pagerState = pagerState,
                            setPageDone = { updatePageDoneValue(page) },
                            profileSettingsUseCases = profileSettingsUseCases
                        )
                    NOTIFICATIONS ->
                        PageNotifications(
                            page = page,
                            pagerState = pagerState,
                            setPageDone = { updatePageDoneValue(page) },
                            notificationSettingsUseCases = notificationSettingsUseCases
                        )
                }
            }
            AnimatedPagerDots(
                count = OnboardingPages.entries.size,
                currentlyAddedPages = pages,
                pagerState = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.1f)
                    .padding(bottom = 24.dp)
            )
        }
    }
}