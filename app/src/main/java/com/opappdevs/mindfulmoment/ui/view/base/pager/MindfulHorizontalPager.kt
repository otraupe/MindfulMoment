package com.opappdevs.mindfulmoment.ui.view.base.pager

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

//TODO: currently kind of pointless
@Composable
fun MindfulHorizontalPager(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    pageContent: @Composable PagerScope.(page: Int) -> Unit
) {
    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        pageContent = pageContent,
    )
}