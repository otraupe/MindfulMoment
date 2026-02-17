package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.domain.usecase.profilesettings.ProfileSettingsUseCases
import com.opappdevs.mindfulmoment.ext.toSimpleDateString
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulTextButton
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPage
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages
import timber.log.Timber
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageProfile(
    page: OnboardingPages,
    pagerState: PagerState,
    setPageDone: (OnboardingPages) -> Unit,
    profileSettingsUseCases: ProfileSettingsUseCases
) {
    Timber.d("PageProfile")

    val context = LocalContext.current

    var profileNameText by rememberSaveable { mutableStateOf(profileSettingsUseCases.getProfileNameUseCase()) }

    var birthDateMillis by rememberSaveable { mutableLongStateOf(profileSettingsUseCases.getBirthdayMillisUseCase()) }
    var birthDateText by rememberSaveable { mutableStateOf(birthDateMillis.toSimpleDateString()) }

    val focusManager = LocalFocusManager.current

    val initialDate = Calendar.getInstance().apply {
        set(2000, Calendar.JANUARY, 1)
    }.timeInMillis
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialDate
    )
    var showDatePickerDialog by remember { mutableStateOf(false) }

    if (showDatePickerDialog) {
        Timber.d("showDatePickerDialog is true")
        DatePickerDialog( //TODO: in separate file
            onDismissRequest = { showDatePickerDialog = false },
            confirmButton = {
                MindfulButton (
                    labelRes = R.string.ui_base_button_ok
                ) {
                        showDatePickerDialog = false // Hide the dialog
                        datePickerState.selectedDateMillis?.let { millis ->
                            birthDateMillis = millis
                            profileSettingsUseCases.setBirthdayMillisUseCase(birthDateMillis)
                            // Format the selected date and update the text field
                            birthDateText = birthDateMillis.toSimpleDateString()
                        }
                    }
            },
            dismissButton = {
                MindfulTextButton(
                    labelRes = R.string.ui_base_button_cancel
                ) {
                    showDatePickerDialog = false
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    OnboardingPage(
        baseContent = page,
        pagerState = pagerState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //TODO: fields vertically centered in the available space?
            OutlinedTextField(
                value = profileNameText,
                onValueChange = {profileNameText = it},
                label = { Text(text = "Name") },
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
                    .padding(top = dimensionResource(R.dimen.mindful_base_text_spacing))
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused) {
                            Timber.d("Profile name text field lost focus")
                            if (!profileNameText.isBlank()) {
                                profileSettingsUseCases.setProfileNameUseCase(profileNameText.trim())
                            }
                        }
                    }
            )
            OutlinedTextField(
                value = birthDateText,
                onValueChange = {}, // No-op, made read-only
                enabled = false,
                label = { Text(text = "Geburtsdatum") },
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.mindful_base_text_spacing))
                    .clickable {
                        focusManager.clearFocus()
                        showDatePickerDialog = true
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
            MindfulButton(
                labelRes = R.string.ui_onboarding_pages_profile_button_primary ,
                modifier = Modifier.padding(
                    top = 48.dp
                )
            ) {
                focusManager.clearFocus()
                if (profileNameText.isBlank()) {
                    Toast.makeText(
                        context,
                        R.string.ui_onboarding_pages_profile_toast_empty_name,
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (birthDateMillis < 0) {
                    Toast.makeText(
                        context,
                        R.string.ui_onboarding_pages_profile_toast_empty_birthday,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    setPageDone(page)
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
//            page = OnboardingPages.PROFILE,
//            pagerState = rememberPagerState { 0 },
//            setPageDone = { (OnboardingPages.PROFILE) }
//        )
//    }
//}