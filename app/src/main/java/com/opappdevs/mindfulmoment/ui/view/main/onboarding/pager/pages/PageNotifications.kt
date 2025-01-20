package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulTextButton
import com.opappdevs.mindfulmoment.ui.view.base.icon.MindfulCheckMark
import com.opappdevs.mindfulmoment.ui.view.base.permission.notificationPermissionButton
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPage
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PageNotifications(
    page: OnboardingPages,
    pagerState: PagerState,
    advancePager: (OnboardingPages) -> Unit
) {
//    val notificationPermissionState = notificationPermissionButton(/*Permission.NOTIFICATION*/)
//    val checkMarkVisible = remember {
//        derivedStateOf { notificationPermissionState?.status?.isGranted ?: false }
//    }
    val checkmarkTransitionState = MutableTransitionState(initialState = false)
    val coroutineScope = rememberCoroutineScope()
    var checkMarkVisible: State<Boolean>? = null

    OnboardingPage(
        baseContent = page,
        pagerState = pagerState
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.mindful_base_card_padding))
        ) {
            Spacer(Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MindfulTextButton(
                    string = "Nicht jetzt"
                ) {
                    advancePager(page)
                }
                val permissionState = notificationPermissionButton(
                    labelRes = R.string.ui_onboarding_pages_notifications_button_primary,
                    modifier = Modifier.padding(top = 4.dp)
                )
                checkMarkVisible = remember {
                    derivedStateOf { permissionState?.status?.isGranted ?: false }
                }
            }
            /*TODO: notification request*/
            //TODO: don't forget to ask for preferred time
            //notificationPermissionState?.launchPermissionRequest()

            //TODO: only after permission granted (and checkmark animation done)
            // or permanently denied
            // by (LaunchedEffect, but only after page has handled permissionState)
            //advancePager(page)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(
                        ratio = 1f,
                        matchHeightConstraintsFirst = false
                    )
            ) {
                androidx.compose.animation.AnimatedVisibility(
                    modifier = Modifier.fillMaxSize(),
                    visibleState = remember {
                        checkmarkTransitionState
                    }.apply { targetState = checkMarkVisible?.value ?: false },
                    enter = scaleIn() + fadeIn(),
                    exit = ExitTransition.None
                ) { // Content that needs to appear/disappear goes here:
                    MindfulCheckMark()
                }
            }
        }
    }
    // TODO: make this more elegant
    LaunchedEffect(checkmarkTransitionState.isIdle) {
        if (checkmarkTransitionState.isIdle && checkmarkTransitionState.currentState) {
            coroutineScope.launch {
                withContext(Dispatchers.IO) {
                    Thread.sleep(1000)
                    withContext(Dispatchers.Main) {
                        advancePager(page)
                    }
                }
            }
        }
    }
}

@ThemePreviews
@Composable
fun PreviewPageNotifications() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        PageNotifications(
            page = OnboardingPages.NOTIFICATIONS,
            pagerState = rememberPagerState { 0 },
            advancePager = {}
        )
    }
}