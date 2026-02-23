package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages

import android.os.Build
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TimePickerSelectionMode
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.NotificationSettingsUseCases
import com.opappdevs.mindfulmoment.ext.toHourMinuteString
import com.opappdevs.mindfulmoment.ui.view.base.MindfulToast.Companion.Duration.SHORT
import com.opappdevs.mindfulmoment.ui.view.base.MindfulToast.Companion.showMindfulToast
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulTextButton
import com.opappdevs.mindfulmoment.ui.view.base.dialog.MindfulTimePickerDialog
import com.opappdevs.mindfulmoment.ui.view.base.icon.MindfulCheckMark
import com.opappdevs.mindfulmoment.ui.view.base.permissions.Permissions
import com.opappdevs.mindfulmoment.ui.view.base.permissions.PermissionButton
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPage
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.Locale

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PageNotifications(
    pageNumber: Int,
    page: OnboardingPages,
    pagerState: PagerState,
    setPageDone: () -> Unit,
    notificationSettingsUseCases: NotificationSettingsUseCases,
    pagesDone: List<OnboardingPages>
) {
    Timber.d("PageNotifications")

    val context = LocalContext.current

    val notificationPermissionRequired = remember {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    }

    val coroutineScope = rememberCoroutineScope()

    val checkMarkVisible = rememberSaveable { mutableStateOf(false) }
    val animateCheckMark = rememberSaveable { mutableStateOf(false) }

    //show checkmark on older devices if notifications already enabled in the app
    LaunchedEffect(Unit) {
        if (!notificationPermissionRequired) {
            if (notificationSettingsUseCases.getNotificationsEnabled()) {
                checkMarkVisible.value = true
            }
        }
    }

    var primaryButtonEnabled by rememberSaveable {
        mutableStateOf(!pagesDone.contains(page))
    }
    var primaryButtonStringRes by rememberSaveable { mutableIntStateOf(R.string.ui_onboarding_pages_notifications_button_primary) }

    var notificationTimeMinutes by rememberSaveable {
        mutableIntStateOf(notificationSettingsUseCases.getNotificationTime())
    }
    var notificationTimeText by rememberSaveable {
        mutableStateOf(notificationTimeMinutes.toHourMinuteString())
    }
    val (showTimePickerDialog, setShowTimePickerDialog) = remember { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState(is24Hour = true)
    val simpleTimeFormattingString = stringResource(R.string.formatting_time_hour_minute)

    fun showTimePicker() {
        if (notificationTimeMinutes < 0) {
            timePickerState.hour = 0
            timePickerState.minute = 0
        } else {
            timePickerState.hour = notificationTimeMinutes / 60
            timePickerState.minute = notificationTimeMinutes % 60
        }
        timePickerState.selection = TimePickerSelectionMode.Hour
        setShowTimePickerDialog(true)
    }

    if (showTimePickerDialog) {
        MindfulTimePickerDialog(
            timePickerState = timePickerState,
            titleRes = null,
            confirmButtonTextRes = R.string.ui_base_button_ok,
            dismissButtonTextRes = R.string.ui_base_button_cancel,
            onConfirm = {
                setShowTimePickerDialog(false)
                // 3. Format the selected time and update the text field
                notificationTimeText = String.format(Locale.getDefault(), simpleTimeFormattingString,
                    timePickerState.hour, timePickerState.minute)

                notificationTimeMinutes = timePickerState.hour * 60 + timePickerState.minute
                notificationSettingsUseCases.setNotificationTime(notificationTimeMinutes)
            },
            onDismiss = { setShowTimePickerDialog(false) },
            onDismissRequest = { setShowTimePickerDialog(false) }
        )
    }

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
                            notificationSettingsUseCases.setNotificationsEnabled()
                            primaryButtonEnabled = false
                            animateCheckMark.value = false
                            setPageDone()
                            Timber.d("is notification enabled: ${notificationSettingsUseCases.getNotificationsEnabled()}")
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
            OutlinedTextField(
                value = notificationTimeText,
                onValueChange = {}, // No-op, made read-only
                enabled = false,
                label = {
                    Text(
                        text = stringResource(R.string.ui_base_label_time),
                        maxLines = 1,
                        autoSize = TextAutoSize.StepBased(
                            minFontSize = 12.sp,
                            maxFontSize = 18.sp
                        )
                    )
                        },
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 22.sp,   //TODO: set up my own typography
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .width(dimensionResource(R.dimen.mindful_base_textField_width))
                    .padding(vertical = dimensionResource(R.dimen.mindful_base_card_sub_spacing))
                    .clickable {
                        Timber.d("showDatePickerDialog set to true")
                        showTimePicker()
                    },
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors().copy(
                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledIndicatorColor = MaterialTheme.colorScheme.outline,
                    disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    //For Icons
                    disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant)
            )
            if (!checkMarkVisible.value) {
                MindfulTextButton(
                    labelRes = R.string.ui_onboarding_pages_notifications_button_secondary,
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

            if (notificationPermissionRequired && notificationTimeMinutes >= 0) {
                PermissionButton(
                    labelRes = primaryButtonStringRes,
                    permission = Permissions.POST_NOTIFICATION,
                    uiVisibleState = checkMarkVisible,
                    uiAnimateState = animateCheckMark,
                    modifier = Modifier.padding(top = 4.dp),
                    getPermissionRequestedBefore = {
                        notificationSettingsUseCases.getNotificationPermissionRequested()
                    },
                    setPermissionRequestedBefore = {
                        notificationSettingsUseCases.setNotificationPermissionRequested()
                    },
                    enabled = primaryButtonEnabled
                ) {
                    notificationSettingsUseCases.setNotificationsEnabled()
                    primaryButtonEnabled = false
                    setPageDone()
                }
            } else {
                MindfulButton(
                    labelRes = primaryButtonStringRes,
                    modifier = Modifier.padding(top = 4.dp),
                    enabled = primaryButtonEnabled
                ) {
                    if (notificationTimeMinutes < 0) {
                         showMindfulToast(
                            context = context,
                            messageRes = R.string.ui_onboarding_pages_notifications_toast_empty_time,
                            duration = SHORT
                         )
                    } else {
                        if (!notificationSettingsUseCases.getNotificationsEnabled()) {
                            animateCheckMark.value = true
                            checkMarkVisible.value = true
                            notificationSettingsUseCases.setNotificationsEnabled()
                        } else {
                            primaryButtonEnabled = false
                            setPageDone()
                        }
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
//            setPageDone = {},
//            notificationSettingsActions = NotificationSettingsUseCases()
//        )
//    }
//}