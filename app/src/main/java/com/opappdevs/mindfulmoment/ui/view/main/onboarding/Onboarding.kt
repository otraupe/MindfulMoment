package com.opappdevs.mindfulmoment.ui.view.main.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.view.basic.AppBackground
import com.opappdevs.mindfulmoment.ui.view.basic.MindfulTextButton

@Composable
fun Onboarding() {
    AppBackground {
        val pagerVisible = remember { mutableStateOf(false) }
        val welcomeVisible = remember { derivedStateOf { !pagerVisible.value } }

        //TODO: hide top bar, white bottom strip
        //TODO: orange text slightly glaring/glowing
        //TODO: think again about button color
        //TODO: horizontal padding for second text
        //TODO: second text gray
        //TODO: create background drawable to load?
        //  research most efficient way
        //TODO: put AnimatedVisibility back into pager
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.bg_heart_on_grass),
            contentDescription = stringResource(R.string.ui_onboarding_background_cd),
            contentScale = ContentScale.FillBounds
        )

        AnimatedVisibility(
            visible = welcomeVisible.value,
            modifier = Modifier.fillMaxSize(),
            enter = EnterTransition.None,
            exit = fadeOut(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = 72.dp),
                    text = AnnotatedString.Companion.fromHtml(
                        stringResource(R.string.ui_onboarding_welcome_title)
                    ),
                    style = MaterialTheme.typography.displayMedium,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = stringResource(R.string.ui_onboarding_welcome_subtitle),
                    style = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(1f))
                MindfulTextButton(
                    modifier = Modifier.padding(80.dp),
                    string = stringResource(R.string.ui_onboarding_startButton_label),
                    onClick = { pagerVisible.value = true }
                )
            }
        }
        AnimatedVisibility(
            visible = pagerVisible.value,
            modifier = Modifier.fillMaxSize(),
            enter = fadeIn() + scaleIn(initialScale = .5f),
            exit = scaleOut(targetScale = .5f) + fadeOut(),
        ) {
            OnboardingPager()
        }
    }
}

@ThemePreviews
@Composable
fun PreviewOnboarding() {
    Onboarding()
}