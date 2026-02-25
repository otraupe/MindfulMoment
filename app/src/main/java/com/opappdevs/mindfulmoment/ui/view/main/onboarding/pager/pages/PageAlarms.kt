package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulTextButton
import com.opappdevs.mindfulmoment.ui.view.base.icon.MindfulCheckMark
import com.opappdevs.mindfulmoment.ui.view.base.permissions.PermissionAlarmsButton
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPage
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PageAlarms(
    pageNumber: Int,
    page: OnboardingPages,
    pagerState: PagerState,
    setPageDone: () -> Unit,
    canScheduleExactAlarms: () -> Boolean,
    pagesDone: List<OnboardingPages>
) {
    Timber.d("PageAlarms")

    val coroutineScope = rememberCoroutineScope()

    val checkMarkVisible = rememberSaveable { mutableStateOf(false) }
    val animateCheckMark = rememberSaveable { mutableStateOf(false) }

    var primaryButtonEnabled by rememberSaveable {
        mutableStateOf(!pagesDone.contains(page))
    }
    var primaryButtonStringRes by rememberSaveable { mutableIntStateOf(R.string.ui_onboarding_pages_alarms_button_primary) }

    val scale by animateFloatAsState(
        targetValue = if (checkMarkVisible.value) 1f else .5f,
        animationSpec = tween(
            durationMillis = if (animateCheckMark.value)
                integerResource(R.integer.ui_animation_check_mark)
            else 0
        ),
        label = "animate notifications check mark scale",
        finishedListener = {
            if (animateCheckMark.value) {
                coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        Thread.sleep(500)
                        withContext(Dispatchers.Main) {
                            primaryButtonEnabled = false
                            animateCheckMark.value = false
                            setPageDone()
                        }
                    }
                }
            }
            else primaryButtonStringRes = R.string.ui_onboarding_pages_notifications_button_primary_alt
        }
    )

    var skipButtonEnabled by rememberSaveable { mutableStateOf(true) }

    val alpha by animateFloatAsState(
        targetValue = if (checkMarkVisible.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = if (animateCheckMark.value)
                integerResource(R.integer.ui_animation_check_mark)
            else 0
        ),
        label = "animate notifications check mark alpha"
    )

    OnboardingPage(
        pageNumber = pageNumber,
        baseContent = page,
        pagerState = pagerState
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!checkMarkVisible.value) {
                MindfulTextButton(
                    labelRes = R.string.ui_onboarding_pages_alarms_button_secondary,
                    enabled = skipButtonEnabled
                ) {
                    skipButtonEnabled = false
                    primaryButtonStringRes = R.string.ui_onboarding_pages_notifications_button_primary_alt2
                    setPageDone()
                }
            } else {
                Box(
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.mindful_base_card_sub_spacing)),
                    contentAlignment = Alignment.Center,
                ) {
                    MindfulCheckMark(
                        modifier = Modifier
                            .size(72.dp)
                            .scale(scale)
                            .alpha(alpha/2)
                    )
                    Text(
                        text = stringResource(R.string.ui_onboarding_pages_notifications_success),
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        modifier = Modifier.alpha(alpha).scale(scale)
                    )
                }
            }
            PermissionAlarmsButton(
                labelRes = primaryButtonStringRes,
                uiVisibleState = checkMarkVisible,
                uiAnimateState = animateCheckMark,
                canScheduleExactAlarms = canScheduleExactAlarms,
                modifier = Modifier.padding(top = 4.dp),
                enabled = primaryButtonEnabled
            ) {
                primaryButtonEnabled = false
                setPageDone()
            }
        }
    }
}

@ThemePreviews
@Composable
fun PreviewPageAlarms() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        PageAlarms(
            pageNumber = 0,
            page = OnboardingPages.ALARMS,
            pagerState = rememberPagerState { 0 },
            setPageDone = { (OnboardingPages.ALARMS) },
            canScheduleExactAlarms = { false },
            pagesDone = listOf()
        )
    }
}