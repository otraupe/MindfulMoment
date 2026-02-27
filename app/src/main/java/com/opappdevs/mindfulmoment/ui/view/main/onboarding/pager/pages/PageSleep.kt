package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.ProfileSettingsUseCases
import com.opappdevs.mindfulmoment.ext.safeToInt
import com.opappdevs.mindfulmoment.ext.toHoursSleepString
import com.opappdevs.mindfulmoment.ui.view.base.MindfulToast.Companion.Duration.SHORT
import com.opappdevs.mindfulmoment.ui.view.base.MindfulToast.Companion.showMindfulToast
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton
import com.opappdevs.mindfulmoment.ui.view.base.dialog.MindfulStringPickerDialog
import com.opappdevs.mindfulmoment.ui.view.base.dialog.rememberPickerState
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
                        },
            onDismiss = { setShowSleepPickerDialog(false) },
            onDismissRequest = { setShowSleepPickerDialog(false) }
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
            OutlinedTextField(
                value = sleepDesiredHoursText,
                onValueChange = {}, // No-op, made read-only
                enabled = false,
                label = {
                    Text(
                        text = stringResource(R.string.ui_base_label_sleep_duration),
                        maxLines = 1,
                        autoSize = TextAutoSize.StepBased(
                            minFontSize = 12.sp,
                            maxFontSize = 18.sp
                        )
                    )
                },
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                ),
                singleLine = true,
                modifier = Modifier
                    .width(dimensionResource(R.dimen.mindful_base_textField_width))
                    .padding(top = dimensionResource(R.dimen.mindful_base_card_sub_spacing))
                    .clickable {
                        setShowSleepPickerDialog(true)
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