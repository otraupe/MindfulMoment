package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages

import android.os.Build
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.NotificationSettingsUseCases
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulTextButton
import com.opappdevs.mindfulmoment.ui.view.base.icon.MindfulCheckMark
import com.opappdevs.mindfulmoment.ui.view.base.permission.Permission
import com.opappdevs.mindfulmoment.ui.view.base.permission.PermissionButton
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPage
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PageNotificationsNew(
    page: OnboardingPages,
    pagerState: PagerState,
    setPageDone: (OnboardingPages) -> Unit,
    notificationSettingsActions: NotificationSettingsUseCases
) {
    val notificationPermissionRequired = remember {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    }

    val coroutineScope = rememberCoroutineScope()
    val checkMarkVisible = remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (checkMarkVisible.value) 1f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "scale",
        finishedListener = {
            if (checkMarkVisible.value) {
                coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        Thread.sleep(1000)
                        withContext(Dispatchers.Main) {
                            setPageDone(page)
                        }
                    }
                }
            }
        }
    )

    val alpha by animateFloatAsState(
        targetValue = if (checkMarkVisible.value) 1f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "alpha"
    )

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
                    setPageDone(page)
                }
                if (notificationPermissionRequired) {
                    PermissionButton(
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
                        //pageDone(page)
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
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(
                        ratio = 1f,
                        matchHeightConstraintsFirst = false
                    )
            ) {
                MindfulCheckMark(
                    modifier = Modifier
                        .fillMaxSize()
                        .scale(scale)
                        .alpha(alpha)
                )
            }
        }
    }
}