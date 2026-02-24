package com.opappdevs.mindfulmoment.ui.view.base.pager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages
import kotlin.math.absoluteValue

@Composable
fun PagerDot(
    pagerState: PagerState,
    page: Int,
    currentlyAddedPages: MutableState<List<OnboardingPages>>
) {
    val offset by remember {
        derivedStateOf {
            if (page > currentlyAddedPages.value.size -1) 1f
            else {
                pagerState.getOffsetDistanceInPages(page)
                    .absoluteValue
                    .coerceAtMost(.5f) * 2
            }
        }
    }
    val color = lerp(
            colorResource(R.color.pager_dot_current),
            colorResource(R.color.pager_dot_other),
            offset
    )
    Box(
        modifier = Modifier
            .width(16.dp)
            .height(16.dp)
            .padding(4.dp)
            .background(
                color = color,
                shape = CircleShape
            )
    )
}