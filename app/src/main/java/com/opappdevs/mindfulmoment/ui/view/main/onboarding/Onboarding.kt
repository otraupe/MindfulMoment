package com.opappdevs.mindfulmoment.ui.view.main.onboarding

import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.navigation.Destinations
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme
import com.opappdevs.mindfulmoment.ui.view.base.MindfulBackground
import com.opappdevs.mindfulmoment.ui.view.base.pager.PagerScrollAnimationSpec
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPager
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages
import timber.log.Timber

@Composable
fun Onboarding(
    navController: NavHostController,
    snackState: SnackbarHostState
) {
    //api lvl 35+ (Android 15) supports edge-to-edge
//    if (Build.VERSION.SDK_INT < 35) {
//        val systemUiController = rememberSystemUiController()
//        systemUiController.setNavigationBarColor(
//            color = colorResource(R.color.system_bars_onboarding)
//        )
//    }
    val viewModel: OnboardingViewModel = hiltViewModel() //scoped to backstack entry
    val pageDone: OnboardingPages? by viewModel.pagerPageDone.collectAsState()
    var firstPageDone = remember { false }
    val pagerState = rememberPagerState { OnboardingPages.entries.size }
    val pagerVisible = remember { mutableStateOf(false) }
    val welcomeVisible = remember { derivedStateOf { !pagerVisible.value } }

    LaunchedEffect(pageDone) {
        Timber.d("Launched effect for pageDone: $pageDone")

        val pdv = pageDone
        if (pdv != null) {
            if (!pdv.isLastPage()) {
                pagerState.animateScrollToPage(
                    page = pagerState.currentPage + 1,
                    animationSpec = PagerScrollAnimationSpec.slowDownScrollAnimationSpec())
                if (pdv.isFirstPage() && !firstPageDone) {
                    snackState.showSnackbar(
                        message = "Swipe zum Zurückgehen",
                        withDismissAction = true,
                        duration = SnackbarDuration.Long
                    )
                    firstPageDone = true //in case we keep pager nav buttons
                }
                // TODO: update swipe limiter
            } else {
                pagerVisible.value = false

                //TODO: callback to wait for animation end
                navController.navigate(Destinations.Home.route)
            }
        }
    }

    MindfulBackground(
        background = {
            Image(
                //create background drawable to load?
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.bg_heart_on_grass),
                contentDescription = stringResource(R.string.ui_onboarding_background_cd),
                contentScale = ContentScale.Crop
            )
        }
    ) {
        AnimatedVisibility(
            visible = welcomeVisible.value,
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.mindful_base_page_padding)),
            enter = EnterTransition.None,
            exit = fadeOut(),
        ) {
            WelcomeContent(pagerVisible)
        }
        OnboardingPager(
            pagerState = pagerState,
            pagerVisible = pagerVisible,
            advancePager = { source -> viewModel.advancePager(source) },
            saveProfile = { userName, birthDate -> viewModel.saveProfile(userName, birthDate) }
        )
    }
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