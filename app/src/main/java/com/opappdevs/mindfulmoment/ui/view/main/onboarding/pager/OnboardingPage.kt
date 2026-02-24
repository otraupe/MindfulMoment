package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager

import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.ui.view.base.MindfulCard
import com.opappdevs.mindfulmoment.ui.view.base.button.icon.icons.MindfulIconButtonBack
import com.opappdevs.mindfulmoment.ui.view.base.button.icon.icons.MindfulIconButtonClose
import com.opappdevs.mindfulmoment.ui.view.base.button.icon.icons.MindfulIconButtonInfo
import com.opappdevs.mindfulmoment.ui.view.base.pager.PagerScrollAnimationSpec
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

//TODO: function a bit long and/or convoluted

@Composable
fun OnboardingPage(
    pageNumber: Int, //TODO: this is not pretty
    baseContent: OnboardingPages,
    pagerState: PagerState,
    focusManager: FocusManager? = null,
    customContent: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()

    val infoVisible = remember { mutableStateOf(false) }

    val offsetDerivedState by remember {
        derivedStateOf {
            val absOffset = pagerState.getOffsetDistanceInPages(pageNumber)
                .absoluteValue
            Pair(1.1f - absOffset, 1f - absOffset / 8) //1.1f: avoid full
        // transparency; could trigger garbage collection (really?)
        }
    }

    MindfulCard(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.8f)
            .graphicsLayer {
                alpha = offsetDerivedState.first
                scaleX = offsetDerivedState.second
                scaleY = offsetDerivedState.second
            }
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
                    painterResource(baseContent.iconRes),
                    tint = MaterialTheme.colorScheme.tertiary,
                    contentDescription = stringResource(baseContent.iconContentDescriptionRes),
                    modifier = Modifier
                        .border(6.dp, MaterialTheme.colorScheme.tertiary, CircleShape)
                        .padding(16.dp)
                        .width(72.dp)
                        .aspectRatio(ratio = 1.0f, matchHeightConstraintsFirst = false)
                )

                // icon buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    if (!baseContent.isFirstPage()) { //TODO: dangerous, should check against pagesToShow.isFirstOrNull()
                        //goes back to previous page
                        MindfulIconButtonBack(
                            contentDescription = stringResource(R.string.ui_base_button_back_cd)
                        ) {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage - 1,
                                    animationSpec = PagerScrollAnimationSpec.deceleratingScrollAnimationSpec()
                                )
                            }
                        }
                    }
                    Spacer(Modifier.weight(1f))
                    baseContent.infoRes?.let {
                        Crossfade(
                            targetState = infoVisible,
                            label = "Info icon button swap"
                        ) { infoVisible ->
                            if (infoVisible.value) {
                                MindfulIconButtonClose(
                                    //goes back from info
                                    contentDescription = stringResource(R.string.ui_base_button_close_cd)
                                ) { infoVisible.value = false }
                            } else {
                                MindfulIconButtonInfo {
                                    focusManager?.clearFocus()
                                    infoVisible.value = true
                                }
                            }
                        }
                    }
                }
            }
            //page title
            Text(
                text = stringResource(baseContent.titleRes),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                maxLines = 1,
                autoSize = TextAutoSize.StepBased(minFontSize = 18.sp, maxFontSize = 28.sp),
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.mindful_base_card_padding))
                    .fillMaxWidth(),
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
                            text = stringResource(baseContent.bodyRes),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontSize = 18.sp,
                                lineHeight = 21.sp,
                                hyphens = Hyphens.Auto, //enable hyphenation
                                lineBreak = LineBreak.Paragraph //higher-quality hyphenation to reduce gaps
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        )
                    }
                    // input elements, buttons and such
                    customContent()
                }
                baseContent.infoRes?.let {
                    androidx.compose.animation.AnimatedVisibility(
                        visible = infoVisible.value,
                        modifier = Modifier.fillMaxSize(),
                        enter = fadeIn(),
                        exit = fadeOut(),
                    ) {
                        Text(
                            text = stringResource(baseContent.infoRes),
                            textAlign = TextAlign.Justify,
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontSize = 18.sp,
                                lineHeight = 21.sp,
                                hyphens = Hyphens.Auto, //enable hyphenation
                                lineBreak = LineBreak.Paragraph //higher-quality hyphenation to reduce gaps
                            ),
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.background,
                                    shape = RectangleShape
                                )
                                .verticalScroll(rememberScrollState())
                        )
                    }
                }
            }
        }
    }
}