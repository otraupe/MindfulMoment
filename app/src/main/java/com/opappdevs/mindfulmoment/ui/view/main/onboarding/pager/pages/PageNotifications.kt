package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages

import android.os.Build
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDialog
import androidx.compose.material3.TimePickerSelectionMode
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.domain.usecase.notificationsettings.NotificationSettingsUseCases
import com.opappdevs.mindfulmoment.ext.toHourMinuteString
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
import timber.log.Timber
import java.util.Locale

// TODO: make this a PermissionPage

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PageNotifications(
    page: OnboardingPages,
    pagerState: PagerState,
    setPageDone: (OnboardingPages) -> Unit,
    notificationSettingsUseCases: NotificationSettingsUseCases
) {
    Timber.d("PageNotifications")

    val context = LocalContext.current

    val notificationPermissionRequired = remember {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    }

    val coroutineScope = rememberCoroutineScope()

    val checkMarkVisible = rememberSaveable { mutableStateOf(false) }
    val animateCheckMark = rememberSaveable { mutableStateOf(false) }

    var primaryButtonEnabled by rememberSaveable { mutableStateOf(true) }
    var primaryButtonStringRes by rememberSaveable { mutableIntStateOf(R.string.ui_onboarding_pages_notifications_button_primary) }

    var notificationTimeMinutes by rememberSaveable { mutableIntStateOf(notificationSettingsUseCases.getNotificationTime()) }
    var notificationTimeText by rememberSaveable { mutableStateOf(notificationTimeMinutes.toHourMinuteString()) }
    val (showTimePickerDialog, setShowTimePickerDialog) = remember { mutableStateOf(false) }

    val timePickerState = rememberTimePickerState(is24Hour = true)

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

    //TODO: separate file
    if (showTimePickerDialog) {
        TimePickerDialog( // You can use a standard Dialog here too
            onDismissRequest = { setShowTimePickerDialog(false) },
            confirmButton = {
                MindfulButton(
                    labelRes = R.string.ui_base_button_ok
                ) {
                    setShowTimePickerDialog(false)
                    // 3. Format the selected time and update the text field
                    notificationTimeText = String.format(Locale.getDefault(), "%02d:%02d",
                        timePickerState.hour, timePickerState.minute)

                    notificationTimeMinutes = timePickerState.hour * 60 + timePickerState.minute
                    notificationSettingsUseCases.setNotificationTime(notificationTimeMinutes)
                }
            },
            title = { Text(text = "Uhrzeit wählen") },
            dismissButton = {
                MindfulTextButton(
                    labelRes = R.string.ui_base_button_cancel
                ) {
                    setShowTimePickerDialog(false)
                }
            }
        ) {
            TimePicker(state = timePickerState)
        }
    }

    val scale by animateFloatAsState(
        targetValue = if (checkMarkVisible.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = if (animateCheckMark.value)
                integerResource(R.integer.ui_animation_permission_check_mark)
            else 0
        ),
        label = "scale",
        finishedListener = {
            if (animateCheckMark.value) {
                primaryButtonEnabled = false
                coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        Thread.sleep(1000)
                        withContext(Dispatchers.Main) {
                            animateCheckMark.value = false
                            setPageDone(page)
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
                integerResource(R.integer.ui_animation_permission_check_mark)
            else 0
        ),
        label = "alpha"
    )

    OnboardingPage(
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
                label = { Text(text = "Uhrzeit") },
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                modifier = Modifier
                    .width(150.dp)
                    .padding(vertical = dimensionResource(R.dimen.mindful_base_text_spacing))
                    .clickable {
                        Timber.d("showDatePickerDialog set to true")
                        showTimePicker()
                    },
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
                    setPageDone(page)
                }
            } else {
                Column(
                    modifier = Modifier
                        .padding(bottom = dimensionResource(R.dimen.mindful_base_text_spacing)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    MindfulCheckMark(
                        modifier = Modifier
                            .size(72.dp)
                            .scale(scale)
                            .alpha(alpha)
                    )
                }
            }

            if (notificationPermissionRequired && notificationTimeMinutes >= 0) {
                PermissionButton(
                    labelRes = primaryButtonStringRes,
                    permission = Permission.NOTIFICATION,
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
                    setPageDone(page)
                }
            } else {
                MindfulButton(
                    labelRes = primaryButtonStringRes,
                    modifier = Modifier.padding(top = 4.dp),
                    enabled = primaryButtonEnabled
                ) {
                    if (notificationTimeMinutes < 0) {
                        Toast.makeText(context, "Bitte wähle eine Uhrzeit", Toast.LENGTH_SHORT).show()
                    } else {
                        notificationSettingsUseCases.setNotificationsEnabled()
                        primaryButtonEnabled = false
                        setPageDone(page)
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