package com.opappdevs.mindfulmoment.ui.view.base.pager

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.navigation.NavHostController
import com.opappdevs.mindfulmoment.navigation.Destinations
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages
import timber.log.Timber


@Composable
fun ControlledHorizontalPager(
    pagerVisible: MutableState<Boolean>,
    pagerState: PagerState,
    startPage: Int,
    pageDone: MutableState<OnboardingPages?>,
    modifier: Modifier = Modifier,
    snackState: SnackbarHostState,
    navController: NavHostController,
    pageContent: @Composable PagerScope.(page: Int) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    fun isForwardScrollingAllowed(pagerState: PagerState, pageDone: MutableState<OnboardingPages?>): Boolean {
        return pageDone.value?.let {
            Timber.d("Page done is ${it.ordinal} - current page is ${pagerState.currentPage}")
            it.ordinal >= pagerState.currentPage
        } ?: false
    }

    var swipeHintPresented by remember { mutableStateOf(false) }
    val canScrollToNext by remember {
        derivedStateOf { isForwardScrollingAllowed(pagerState, pageDone) }
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    if (dragAmount.x > 0) {
                        // Allow backward scrolling (right swipe)
                        //change.consume()
                    } else {
                        // Prevent forward scrolling (left swipe)
                        //change.consume()
                        if (true) {
                            change.consume()
                            return@detectDragGestures
                        }
                    }
                }
            },
        pageContent = pageContent
    )
//    LaunchedEffect(canForwardScrollToPage.intValue, pagerState.currentPage) {
//        canScrollToNext =
//    }

    LaunchedEffect(pageDone) {
        Timber.d("Launched effect for pageDone: $pageDone")

        val pdv = pageDone.value
        if (pdv != null) {
            if (!pdv.isLastPage()) {
                pagerState.animateScrollToPage(
                    page = pagerState.currentPage + 1,
                    animationSpec = PagerScrollAnimationSpec.slowDownScrollAnimationSpec())
                if (pdv.isFirstPage() && !swipeHintPresented) {
                    snackState.showSnackbar(
                        message = "Swipe zum Zurückgehen",
                        withDismissAction = true,
                        duration = SnackbarDuration.Long
                    )
                    swipeHintPresented = true //in case we keep pager nav buttons
                }
                // TODO: update swipe limiter
            } else {
                pagerVisible.value = false

                //TODO: callback to wait for animation end
                navController.navigate(Destinations.Home.route)
            }
        }
    }
}

//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Button(
//                onClick = {
//                    coroutineScope.launch {
//                        if (pagerState.currentPage > 0) {
//                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
//                        }
//                    }
//                },
//                enabled = pagerState.currentPage > 0
//            ) {
//                Text("Previous")
//            }
//
//            Button(
//                onClick = {
//                    canScrollToNext = true
//                },
//                enabled = !canScrollToNext && pagerState.currentPage < pageCount - 1
//            ) {
//                Text("Enable Next")
//            }
//
//            Button(
//                onClick = {
//                    coroutineScope.launch {
//                        if (canScrollToNext && pagerState.currentPage < pageCount - 1) {
//                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
//                            canScrollToNext = false
//                        }
//                    }
//                },
//                enabled = canScrollToNext && pagerState.currentPage < pageCount - 1
//            ) {
//                Text("Next")
//            }
//        }