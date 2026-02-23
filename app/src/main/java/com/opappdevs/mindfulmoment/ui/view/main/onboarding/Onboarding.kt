package com.opappdevs.mindfulmoment.ui.view.main.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme
import com.opappdevs.mindfulmoment.ui.view.base.MindfulBackground
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPager
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages

@Composable
fun Onboarding(
    navController: NavHostController,
    snackState: SnackbarHostState
) {
    //TODO: seems to work fine on Android 10
    //api lvl 35+ (Android 15) supports edge-to-edge
//    if (Build.VERSION.SDK_INT < 35) {
//        val systemUiController = rememberSystemUiController()
//        systemUiController.setNavigationBarColor(
//            color = colorResource(R.color.system_bars_onboarding)
//        )
//    }
    val viewModel: OnboardingViewModel = hiltViewModel() //scoped to backstack entry

    val pagesToShow = if (viewModel.canScheduleExactAlarms()) {
        OnboardingPages.entries.filter { it != OnboardingPages.ALARMS }
    } else {
        OnboardingPages.entries
    }

    val welcomeVisible = remember { mutableStateOf(true) }
    val pagerTransitionState = remember {
        MutableTransitionState(initialState = false)
    }

    AnimatedVisibility(
        visible = welcomeVisible.value,
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.mindful_base_page_padding)),
        enter = EnterTransition.None,
        exit = fadeOut(),
    ) {
        WelcomeContent(welcomeVisible, pagerTransitionState)
    }
    OnboardingPager(
        pagesToShow = pagesToShow,
        snackHostState = snackState,
        navHostController = navController,
        pagerTransitionState = pagerTransitionState,
        notificationSettingsUseCases = viewModel.notificationSettingsUseCases,
        profileSettingsUseCases = viewModel.profileSettingsUseCases,
        canScheduleExactAlarms = { viewModel.canScheduleExactAlarms() }
    )
}

@ThemePreviews
@Composable
fun PreviewOnboarding() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        Onboarding(
            rememberNavController(),
            SnackbarHostState()
        )
    }
}