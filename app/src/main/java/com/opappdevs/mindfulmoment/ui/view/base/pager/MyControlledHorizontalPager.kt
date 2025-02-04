package com.opappdevs.mindfulmoment.ui.view.base.pager

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun MyControlledHorizontalPager(
    pagerState: PagerState,
    pageCount: Int,
    limitPage: Int,
    modifier: Modifier = Modifier,
    pageContent: @Composable PagerScope.(page: Int) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var isDraggingForward by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = !isDraggingForward,
            modifier = Modifier
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        if (delta < 0 && pagerState.currentPage >= limitPage) {
                            // Prevent forward scrolling beyond limitPage
                            isDraggingForward = true
                            coroutineScope.launch {
                                pagerState.scrollToPage(limitPage)
                            }
                        } else {
                            isDraggingForward = false
                        }
                    },
                    onDragStopped = {
                        isDraggingForward = false
                    }
                ),
            pageContent = pageContent
        )
    }
}