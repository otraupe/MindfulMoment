package com.opappdevs.mindfulmoment.ui.view.base.pager

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.navigation.NavHostController
import com.opappdevs.mindfulmoment.navigation.Destinations
import com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.OnboardingPages
import timber.log.Timber


@Composable
fun ControlledHorizontalPager(
    pagerVisible: MutableState<Boolean>,
    pagerState: PagerState,
    pageDone: MutableState<OnboardingPages?>,
    modifier: Modifier = Modifier,
    snackState: SnackbarHostState,
    navController: NavHostController,
    onLastPageDone: () -> Unit,
    pageContent: @Composable PagerScope.(page: Int) -> Unit
) {
    //-1 for no pages are done yet
    fun isForwardScrollProhibited() = pagerState.currentPage > (pageDone.value?.ordinal ?: -1) //&& pagerState.currentPage < 2 //TODO: exemption of last page preserves text scrolling there
    var swipeHintPresented by remember { mutableStateOf(false) }

    //TODO: solution prevents vertical scrolling of content
    //  better add pages programmatically; then we also have
    //  the nice overscroll effect
    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .pointerInput(Unit) {
                awaitEachGesture {
                    do {
                        val event: PointerEvent = awaitPointerEvent(
                            pass = PointerEventPass.Initial
                        )
                        event.changes.forEach {
                            val diffX = it.position.x - it.previousPosition.x
                            if (diffX < 0 && isForwardScrollProhibited()) {
                                it.consume()
                            }
                        }
                    } while (event.changes.any { it.pressed })
                }
            }
        ,
        pageContent = pageContent,
    )

    LaunchedEffect(pageDone.value) {
        Timber.d("LaunchedEffect pageDone.value ${pageDone.value}")
        val pdv = pageDone.value
        if (pdv != null) {
            if (!pdv.isLastPage()) {
                pagerState.animateScrollToPage(
                    page = pagerState.currentPage + 1,
                    animationSpec = PagerScrollAnimationSpec.deceleratingScrollAnimationSpec()
                )
                if (pdv.isFirstPage() && !swipeHintPresented) { //TODO: ensure pdv cannot get smaller
                    snackState.showSnackbar(
                        message = "Wischen zum Zurückgehen",
                        actionLabel = "OK",
                        duration = SnackbarDuration.Long
                    )
                    swipeHintPresented = true //in case we keep pager nav buttons //TODO: could be removed
                }
            } else {
                pagerVisible.value = false
                onLastPageDone()

                //TODO: callback to wait for animation end
                navController.navigate(Destinations.Home.route) {
                    popUpTo(Destinations.Onboarding.route) {
                        inclusive = true
                    }
                }
            }
        }
    }
}