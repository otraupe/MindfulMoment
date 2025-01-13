package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager

import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.ui.view.base.MindfulCard
import com.opappdevs.mindfulmoment.ui.view.base.button.icon.icons.MindfulIconButtonForward
import com.opappdevs.mindfulmoment.ui.view.base.button.icon.icons.MindfulIconButtonInfo
import kotlin.math.absoluteValue

@Composable
fun OnboardingPage(
    baseContent: OnboardingPages,
    pagerState: PagerState,
    customContent: @Composable () -> Unit
) {
    val infoVisible = remember { mutableStateOf(false) }

    val offsetDerivedState by remember {
        derivedStateOf {
            val absOffset = pagerState.getOffsetDistanceInPages(baseContent.ordinal)
                .absoluteValue
                Pair(1f - absOffset, 1f - absOffset / 8)
        }
    }
    MindfulCard(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.8f)
            .alpha(offsetDerivedState.first)
            .scale(offsetDerivedState.second)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // top icons
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                Icon(
                    imageVector = baseContent.iconVector,
                    tint = MaterialTheme.colorScheme.tertiary,
                    contentDescription = stringResource(baseContent.iconContentDescriptionRes),
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.mindful_base_card_padding))
                        .width(72.dp)
                        .aspectRatio(ratio = 1.0f, matchHeightConstraintsFirst = false)

                )
                // info icon button
                baseContent.infoRes?.let {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Crossfade(
                            targetState = infoVisible,
                            label = "Info icon button swap"
                        ) { infoVisible ->
                            if (infoVisible.value) {
                                MindfulIconButtonForward(
                                    //goes back from info
                                    contentDescription = stringResource(R.string.ui_base_back_button_cd)
                                ) { infoVisible.value = false }
                            } else {
                                MindfulIconButtonInfo { infoVisible.value = true }
                            }
                        }
                    }
                }
            }
            // autoscale title    // TODO: text scaling is broken
//            MindfullyScalingText(
//                text = stringResource(baseContent.titleRes),
//                textAlign = TextAlign.Center,
//                style = MaterialTheme.typography.headlineMedium,
//                modifier = Modifier.fillMaxWidth(),
//            )
            Text(
                text = stringResource(baseContent.titleRes),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth(),
            )
            // body
            Box(
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.mindful_base_card_padding))
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .verticalScroll(rememberScrollState()),
                    ) {
                        Text(
                            text = stringResource(baseContent.bodyRes), //TODO: does it scroll?
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        )
                    }

                    // textfields, buttons and such
                    customContent() //TODO: put main Button into default page
                }
                //TODO: wieder raus wischen lassen
                baseContent.infoRes?.let {
                    androidx.compose.animation.AnimatedVisibility(
                        visible = infoVisible.value,
                        modifier = Modifier.fillMaxSize(),
                        enter = slideIn { size -> IntOffset(size.width, 0) } + fadeIn(),
                        exit = fadeOut() + slideOut { size -> IntOffset(size.width, 0) },
                    ) {
                        Text(
                            text = stringResource(baseContent.infoRes),
                            textAlign = TextAlign.Justify,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.background,
                                    shape = RectangleShape
                                )
                                .padding(
                                    dimensionResource(R.dimen.mindful_base_card_padding)
                                )
                                .verticalScroll(rememberScrollState())
                        )
                    }
                }
            }
        }
    }
}