package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.ProfileSettingsUseCases
import com.opappdevs.mindfulmoment.ext.toSimpleDateString
import com.opappdevs.mindfulmoment.ui.view.base.MindfulToast.Companion.Duration.SHORT
import com.opappdevs.mindfulmoment.ui.view.base.MindfulToast.Companion.showMindfulToast
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton
import com.opappdevs.mindfulmoment.ui.view.base.dialog.MindfulDatePickerDialog
import com.opappdevs.mindfulmoment.ui.view.base.savers.TextFieldValueSaver
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPage
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages
import timber.log.Timber
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageProfile(
    pageNumber: Int,
    page: OnboardingPages,
    pagerState: PagerState,
    setPageDone: () -> Unit,
    profileSettingsUseCases: ProfileSettingsUseCases,
    pagesDone: List<OnboardingPages>
) {
    Timber.d("PageProfile")

    val context = LocalContext.current

    var primaryButtonEnabled by rememberSaveable {
        mutableStateOf(!pagesDone.contains(page))
    }

    var profileNameText by rememberSaveable(stateSaver = TextFieldValueSaver) {
        mutableStateOf(
            TextFieldValue(profileSettingsUseCases.getProfileNameUseCase())
        )
    }
    var birthDateMillis by rememberSaveable {
        mutableLongStateOf(
            profileSettingsUseCases.getBirthdayMillisUseCase()
        )
    }
    var birthDateText by rememberSaveable { mutableStateOf(birthDateMillis.toSimpleDateString()) }

    val focusManager = LocalFocusManager.current

    val initialDate = Calendar.getInstance().apply {
        set(2000, Calendar.JANUARY, 1)
    }.timeInMillis
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialDate
    )
    val (showDatePickerDialog, setShowDatePickerDialog) = remember { mutableStateOf(false) }

    if (showDatePickerDialog) {
        Timber.d("showDatePickerDialog is true")
        MindfulDatePickerDialog(
            datePickerState = datePickerState,
            titleRes = null, //string is badly positioned in DatePicker
            confirmButtonTextRes = R.string.ui_base_button_ok,
            dismissButtonTextRes = R.string.ui_base_button_cancel,
            onConfirm = {
                setShowDatePickerDialog(false) // Hide the dialog
                datePickerState.selectedDateMillis?.let { millis ->
                    birthDateMillis = millis
                    profileSettingsUseCases.setBirthdayMillisUseCase(birthDateMillis)
                    // Format the selected date and update the text field
                    birthDateText = birthDateMillis.toSimpleDateString()
                }
            },
            onDismiss = { setShowDatePickerDialog(false) },
            onDismissRequest = { setShowDatePickerDialog(false) }
        )
    }

    OnboardingPage(
        pageNumber = pageNumber,
        baseContent = page,
        pagerState = pagerState,
        focusManager = focusManager
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = profileNameText,
                onValueChange = { profileNameText = it },
                label = {
                    Text(
                        text = stringResource(R.string.ui_base_label_name),
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
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                modifier = Modifier
                    .width(dimensionResource(R.dimen.mindful_base_textField_width))
                    .padding(top = dimensionResource(R.dimen.mindful_base_card_sub_spacing))
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            Timber.d("Profile name text field gained focus")
                            profileNameText = profileNameText.copy(
                                selection = TextRange(
                                    0,
                                    profileNameText.text.length
                                )
                            )
                        } else {
                            Timber.d("Profile name text field lost focus")
                            if (!profileNameText.text.isBlank()) {
                                val trimmedText = profileNameText.text.trim()
                                profileSettingsUseCases.setProfileNameUseCase(trimmedText)
                                profileNameText = profileNameText.copy(text = trimmedText)
                            }
                        }
                    },
                shape = RoundedCornerShape(8.dp)
            )
            OutlinedTextField(
                value = birthDateText,
                onValueChange = {}, // No-op, made read-only
                enabled = false,
                label = {
                    Text(
                        text = stringResource(R.string.ui_base_label_birthdate),
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
                        focusManager.clearFocus()
                        setShowDatePickerDialog(true)
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
                labelRes = R.string.ui_onboarding_pages_profile_button_primary ,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.mindful_base_card_padding)
                ),
                enabled = primaryButtonEnabled
            ) {
                focusManager.clearFocus()
                if (profileNameText.text.isBlank()) {
                    showMindfulToast(
                        context = context,
                        messageRes = R.string.ui_onboarding_pages_profile_toast_empty_name,
                        duration = SHORT
                    )
                } else if (birthDateMillis < 0) {
                    showMindfulToast(
                        context = context,
                        messageRes = R.string.ui_onboarding_pages_profile_toast_empty_birthday,
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
//fun PreviewPageProfile() {
//    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
//        PageProfile (
//            pageNumber = 0,
//            page = OnboardingPages.PROFILE,
//            pagerState = rememberPagerState { 0 },
//            setPageDone = { (OnboardingPages.PROFILE) },
//            profileSettingsUseCases = ProfileSettingsUseCases,
//            pagesDone = listOf()
//        )
//    }
//}