package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages

import android.os.Build
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.NotificationSettingsUseCases
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulTextButton
import com.opappdevs.mindfulmoment.ui.view.base.icon.MindfulCheckMark
import com.opappdevs.mindfulmoment.ui.view.base.permission.Permission
import com.opappdevs.mindfulmoment.ui.view.base.permission.permissionButton
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
    pageDone: (OnboardingPages) -> Unit,
    notificationSettingsActions: NotificationSettingsUseCases
) {
//    val notificationPermissionState = notificationPermissionButton(/*Permission.NOTIFICATION*/)
//    val checkMarkVisible = remember {
//        derivedStateOf { notificationPermissionState?.status?.isGranted ?: false }
//    }
    val notificationPermissionRequired = remember {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    }

    val checkmarkTransitionState = MutableTransitionState(initialState = false)
    val coroutineScope = rememberCoroutineScope()
    val checkMarkVisible = remember { mutableStateOf(false) }

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
                    pageDone(page)
                }
                if (notificationPermissionRequired) {
                    val permissionState = permissionButton(
                        labelRes = R.string.ui_onboarding_pages_notifications_button_primary,
                        permission = Permission.NOTIFICATION,
                        uiVisibleState = checkMarkVisible,
                        modifier = Modifier.padding(top = 4.dp),
                        getPermissionRequestedBefore = {
                            notificationSettingsActions.getNotificationPermissionRequested()
                        },
                        setPermissionRequestedBefore = {
                            notificationSettingsActions.setNotificationPermissionRequested()
                        }
                    ) {
                        //TODO: store notification time
                    }
                } else {
                    MindfulButton(
                        labelRes = R.string.ui_onboarding_pages_notifications_button_primary,
                        modifier = Modifier.padding(top = 4.dp),
                        enabled = false //TODO: enable after time set
                    ) {
                        //TODO: store notification time
                    }
                }
//                checkMarkVisible = remember {
//                    derivedStateOf { permissionState?.status?.isGranted ?: false }
//                }
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
                    }.apply { targetState = checkMarkVisible.value },
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
                        pageDone(page)
                    }
                }
            }
        }
    }
}

//@ThemePreviews
//@Composable
//fun PreviewPageNotifications() {
//    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
//        PageNotifications(
//            page = OnboardingPages.NOTIFICATIONS,
//            pagerState = rememberPagerState { 0 },
//            advancePager = {}
//        )
//    }
//}