package com.opappdevs.mindfulmoment.ui.view.base.pager

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.opappdevs.mindfulmoment.R
import kotlin.math.absoluteValue

@Composable
fun PagerDot(
    pagerState: PagerState,
    page: Int
) {
    val offsetState = remember {
        derivedStateOf {
            pagerState.getOffsetDistanceInPages(page)
                .absoluteValue
                .coerceAtMost(.5f) * 2
        }
    }
    val colorAnimation by animateColorAsState(
        targetValue = lerp(
            colorResource(R.color.pager_dot_current),
            colorResource(R.color.pager_dot_other),
            offsetState.value),
        label = "animate pager dot color"
    )

    Box(
        modifier = Modifier
            .width(16.dp)
            .height(16.dp)
            .padding(4.dp)
            .background(
                color = colorAnimation,
                shape = CircleShape
            )
    )
}