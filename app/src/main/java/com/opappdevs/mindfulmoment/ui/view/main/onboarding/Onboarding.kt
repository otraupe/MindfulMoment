package com.opappdevs.mindfulmoment.ui.view.main.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme
import com.opappdevs.mindfulmoment.ui.view.base.MindfulCard
import com.opappdevs.mindfulmoment.ui.view.base.MindfulBackground
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulTextButton
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPager

@Composable
fun Onboarding() {

    //TODO: only for sub 35
    val systemUiController = rememberSystemUiController()
    systemUiController.setNavigationBarColor(
        color = colorResource(R.color.system_bars_onboarding)
    )

    MindfulBackground(
        background = {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.bg_heart_on_grass),
                contentDescription = stringResource(R.string.ui_onboarding_background_cd),
                contentScale = ContentScale.Crop
                //TODO: create multiple version for devices/oris
            )
        }
    ) {
        val pagerVisible = remember { mutableStateOf(false) }
        val welcomeVisible = remember { derivedStateOf { !pagerVisible.value } }

        //TODO: create background drawable to load?
        //  research most efficient way



        AnimatedVisibility(
            visible = welcomeVisible.value,
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.default_page_padding)),
            enter = EnterTransition.None,
            exit = fadeOut(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MindfulCard(
                    modifier = Modifier.padding(top = 64.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp),
                        text = stringResource(R.string.ui_onboarding_welcome_title),
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                        ),
                        color = colorResource(R.color.heart_red),
                        textAlign = TextAlign.Center
                    )
                }
                //TODO: define default some text colors
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = stringResource(R.string.ui_onboarding_welcome_subtitle),
                    style = MaterialTheme.typography.displaySmall.copy(
                        color = colorResource(R.color.dark_gray),
                        fontWeight = FontWeight.SemiBold
                    ),
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.weight(1f))
                MindfulTextButton(
                    modifier = Modifier.padding(48.dp),
                    string = stringResource(R.string.ui_onboarding_startButton_label),
                    onClick = { pagerVisible.value = true }
                )
            }
        }
        OnboardingPager(pagerVisible)
    }
}

@ThemePreviews
@Composable
fun PreviewOnboarding() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        Onboarding()
    }
}