package com.opappdevs.mindfulmoment.ui.view.main.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.opappdevs.mindfulmoment.ui.view.basic.pagers.AppBackground

@Composable
fun Onboarding() {
    AppBackground {
        val pagerVisible = remember { mutableStateOf(false) }
        val welcomeVisible = remember { derivedStateOf { !pagerVisible.value } }

        AnimatedVisibility(
            visible = welcomeVisible.value,
            modifier = Modifier.fillMaxSize(),
            enter = EnterTransition.None,
            exit = fadeOut(),
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Willkommen\nzu deiner neuen\nAchtsamkeits-App"
                )
                //TODO: define more boxy standard button (RED)
                //TODO: get some basic Typography going
                Button(
                    onClick = { pagerVisible.value = true }
                ) {
                    Text(text = "Los geht's")
                }
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