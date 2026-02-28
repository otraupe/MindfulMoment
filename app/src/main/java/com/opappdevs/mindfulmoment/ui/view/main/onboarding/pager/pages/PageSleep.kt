package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.ProfileSettingsUseCases
import com.opappdevs.mindfulmoment.ext.safeToInt
import com.opappdevs.mindfulmoment.ext.toHoursSleepString
import com.opappdevs.mindfulmoment.ui.view.base.MindfulToast.Companion.Duration.SHORT
import com.opappdevs.mindfulmoment.ui.view.base.MindfulToast.Companion.showMindfulToast
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton
import com.opappdevs.mindfulmoment.ui.view.base.dialog.MindfulAlertDialog
import com.opappdevs.mindfulmoment.ui.view.base.dialog.MindfulStringPickerDialog
import com.opappdevs.mindfulmoment.ui.view.base.dialog.rememberPickerState
import com.opappdevs.mindfulmoment.ui.view.base.text.MindfulClickableTextField
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPage
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageSleep(
    pageNumber: Int,
    page: OnboardingPages,
    pagerState: PagerState,
    setPageDone: () -> Unit,
    profileSettingsUseCases: ProfileSettingsUseCases,
    pagesDone: List<OnboardingPages>
) {
    Timber.d("PageSleep")

    val context = LocalContext.current

    var primaryButtonEnabled by rememberSaveable {
        mutableStateOf(!pagesDone.contains(page))
    }

    var sleepDesiredHours by rememberSaveable {
        mutableIntStateOf(
            profileSettingsUseCases.getSleepDurationHoursUseCase()
        )
    }
    var sleepDesiredHoursText by rememberSaveable { mutableStateOf(sleepDesiredHours.toHoursSleepString()) }

    val (showSleepWarningDialog, setShowSleepWarningDialog) = remember { mutableStateOf(false) }

    val pickerHours = remember { 1..24 }
    val startIndex by remember {
        derivedStateOf { pickerHours.indexOf(if (sleepDesiredHours < 0) 8 else sleepDesiredHours) }
    }
    val (showSleepPickerDialog, setShowSleepPickerDialog) = remember { mutableStateOf(false) }
    val sleepHoursPickerState = rememberPickerState()
    if (showSleepPickerDialog) {
        MindfulStringPickerDialog(
            stringPickerState = sleepHoursPickerState,
            stringItems = pickerHours.map { it.toString() },
            startIndex = startIndex,
            titleRes = null,
            confirmButtonTextRes = R.string.ui_base_button_ok,
            dismissButtonTextRes = R.string.ui_base_button_cancel,
            onConfirm = {
                setShowSleepPickerDialog(false)
                sleepDesiredHours = sleepHoursPickerState.selectedItem.safeToInt()
                profileSettingsUseCases.setSleepDurationHoursUseCase(sleepDesiredHours)
                sleepDesiredHoursText = sleepDesiredHours.toHoursSleepString()
                if (!(7..10).contains(sleepDesiredHours)) {
                    setShowSleepWarningDialog(true)
                }
                        },
            onDismiss = { setShowSleepPickerDialog(false) },
            onDismissRequest = { setShowSleepPickerDialog(false) }
        )
    }

    if (showSleepWarningDialog) {
        MindfulAlertDialog(
            titleRes = R.string.ui_base_label_tip,
            textRes = R.string.ui_onboarding_pages_sleep_hours_warning_text,
            confirmButtonTextRes = R.string.ui_base_button_ok,
            dismissButtonTextRes = null,
            onConfirm = { setShowSleepWarningDialog(false) },
            onDismiss = {},
            onDismissRequest = {}
        )
    }

    OnboardingPage(
        pageNumber = pageNumber,
        baseContent = page,
        pagerState = pagerState,
        infoButtonRes = R.string.ui_base_label_tips
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MindfulClickableTextField(
                labelRes = R.string.ui_base_label_sleep_duration,
                textValue = sleepDesiredHoursText,
            ) {
                setShowSleepPickerDialog(true)
            }
            MindfulButton(
                labelRes = R.string.ui_onboarding_pages_sleep_button_primary ,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.mindful_base_card_padding)
                ),
                enabled = primaryButtonEnabled
            ) {
                if (sleepDesiredHours < 0) {
                    showMindfulToast(
                        context = context,
                        messageRes = R.string.ui_onboarding_pages_sleep_toast_empty_hours,
                        duration = SHORT
                    )
                } else {
                    primaryButtonEnabled = false
                    setPageDone()
                }
            }
        }
    }
}

//@ThemePreviews
//@Composable
//fun PreviewPageSleep() {
//    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
//        PageSleep (
//            pageNumber = 0,
//            page = OnboardingPages.SLEEP,
//            pagerState = rememberPagerState { 0 },
//            setPageDone = { (OnboardingPages.SLEEP) },
//            profileSettingsUseCases = ProfileSettingsUseCases,
//            pagesDone = listOf()
//        )
//    }
//}