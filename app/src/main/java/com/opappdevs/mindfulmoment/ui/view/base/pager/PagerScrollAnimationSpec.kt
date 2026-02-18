package com.opappdevs.mindfulmoment.ui.view.base.pager

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.spring

class PagerScrollAnimationSpec {
    companion object {

        //TODO: clean-up

        // Custom Easing to create a smoother, slower start and quicker end
        private val slowDownEasing = Easing { fraction ->
            // You can adjust the exponent to control the easing curve.
            // Higher values result in a slower start.
            fraction * fraction * fraction // Example: Cubic easing (x^3)
        }

        // Custom Easing for a more natural feel with a slight overshoot at the end, then settle.
        private val naturalEasing = Easing { fraction ->
            // Simulate a natural movement with overshoot and settling
            val c4 = (2 * Math.PI) / 3
            val overshoot = 1.15 // Adjust for more or less overshoot

            if (fraction < 0.5) {
                (fraction * 2).let { it * it * (overshoot * it - (overshoot - 1)) }
            } else {
                (fraction * 2 - 2).let { it * it * (overshoot * it + (overshoot - 1)) + 2 } / 2f
            }.toFloat()
        }

        /**
         * Creates an AnimationSpec to slow down programmatic scrolling of a HorizontalPager.
         *
         * @param durationMillis The duration of the scroll animation in milliseconds.
         *                     Defaults to a longer duration for a slower scroll.
         * @param easing The easing function to use for the animation.
         *               Defaults to a custom `SlowDownEasing` for a smooth slowdown effect,
         *               but you can also try `NaturalEasing` for a more dynamic feel.
         * @return An AnimationSpec<Float> that can be used to animate the scrolling.
         */
        fun slowDownScrollAnimationSpec(
            durationMillis: Int = 800, // Increased duration for a slower scroll
            easing: Easing = naturalEasing // Or try NaturalEasing
        ): AnimationSpec<Float> {
            return spring(
                //dampingRatio = 0.7f, // Adjust for a slight bounce effect, if desired
                dampingRatio = 1f, // Adjust for a slight bounce effect, if desired
                stiffness = 100f // Lower stiffness for slower response
            )

            /* Alternatively, you can use TweenSpec for finer control over the animation:
            return TweenSpec(
                durationMillis = durationMillis,
                easing = easing
            )
            */
        }

        /**
         * Alternative easing to simulate scroll deceleration
         */
        private val decelerateEasing = Easing { fraction ->
            1f - (1f - fraction) * (1f - fraction)
        }

        /**
         *  Alternative AnimationSpec using DecelerateEasing
         */
        fun deceleratingScrollAnimationSpec(
            durationMillis: Int = 500
        ): AnimationSpec<Float> = TweenSpec(
            durationMillis = durationMillis,
            easing = decelerateEasing
        )

// Example usage within your HorizontalPager's programmatic scroll logic:
        /*
        val pagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()

        Button(onClick = {
            coroutineScope.launch {
                pagerState.animateScrollToPage(
                    page = pagerState.currentPage + 1,
                    // Use the custom animation spec here:
                    animationSpec = slowDownScrollAnimationSpec()
                    // or
                    // animationSpec = deceleratingScrollAnimationSpec()
                )
            }
        }) {
            Text("Scroll to Next Page")
        }
        */
    }
}

/**
 * Explanation and Customization Options:
 *
 * slowDownScrollAnimationSpec(durationMillis: Int = 800, easing: Easing = SlowDownEasing):
 *
 * durationMillis: This parameter controls the total time the animation takes. Increasing this value will make the scroll slower. The default is set to 800 milliseconds, which is longer than the default HorizontalPager animation.
 * easing: This is where the magic happens. An easing function defines how the animation's value changes over time.
 * SlowDownEasing (Custom): This easing function makes the animation start very slowly and gradually speed up. It uses a cubic easing curve (fraction * fraction * fraction).
 * NaturalEasing (Custom): This provides a more realistic feel by incorporating a slight overshoot at the end before settling into the final position. The parameters c4 and overshoot within the function are tunable.
 * LinearEasing (Compose Default): This would result in a constant speed throughout the animation, making it look robotic and not recommended for slowing down the scroll.
 * FastOutSlowInEasing (Compose Built-in): This starts fast and slows down towards the end, which is the opposite of what we want for a "slow down" effect.
 * FastOutLinearInEasing (Compose Built-in): This starts fast and ends at a constant speed, again not ideal.
 * LinearOutSlowInEasing (Compose Built-in): This starts at a constant speed and slows down, which is a little better but still doesn't have the gradual slowdown effect we're aiming for.
 * spring(...) or TweenSpec(...):
 *
 * spring(...): This creates a spring-based animation, which can add a nice, natural feel to the scroll.
 * dampingRatio: Controls the "bounciness." 1f means no bounce, 0f means it will oscillate forever. Values between 0.5f and 1f are often suitable. The default of 0.7f provides a subtle bounce.
 * stiffness: Controls how quickly the animation tries to reach its target. Lower values mean a slower, more gradual movement. The default of 50f is relatively low.
 * TweenSpec(...): This creates a time-based animation. It is the one I'd recommend to use together with the custom Easings SlowDownEasing or NaturalEasing.
 * deceleratingScrollAnimationSpec(durationMillis: Int = 600) and DecelerateEasing:
 *
 * This provides an alternative approach where the animation starts at a normal speed and then gradually slows down, mimicking the effect of friction.
 * DecelerateEasing creates this deceleration effect using a quadratic curve.
 * How to Use:
 *
 * Copy the code into your project.
 * In your HorizontalPager's programmatic scroll logic (e.g., inside a Button's onClick), use the pagerState.animateScrollToPage() function.
 * Pass the animationSpec you created using slowDownScrollAnimationSpec() or deceleratingScrollAnimationSpec() to the animationSpec parameter of animateScrollToPage().
 * Example:
 *
 * Kotlin
 *
 * val pagerState = rememberPagerState()
 * val coroutineScope = rememberCoroutineScope()
 *
 * Button(onClick = {
 *     coroutineScope.launch {
 *         pagerState.animateScrollToPage(
 *             page = pagerState.currentPage + 1,
 *             animationSpec = slowDownScrollAnimationSpec(durationMillis = 1200, easing = NaturalEasing)
 *         )
 *     }
 * }) {
 *     Text("Slow Scroll to Next Page")
 * }
 * Key Improvements:
 *
 * Custom Easing: The provided SlowDownEasing or NaturalEasing are specifically designed to create the desired slow-down effect.
 * Increased Duration: The default durationMillis is increased to make the animation slower.
 * Spring Animation (Optional): The use of spring() adds a more natural feel compared to a simple time-based animation.
 * Clear Explanation and Customization: The comments explain each parameter and suggest alternative values.
 * Alternative deceleratingScrollAnimationSpec: A different easing function is added for a different style of deceleration, providing you with more options.
 * Tunable Parameters: All of the easing functions have been written to make it easy to adjust key features such as speed and "overshoot".
 * Remember to experiment with the durationMillis, easing, dampingRatio, and stiffness values to fine-tune the animation to your liking! Let me know if you have more questions.
 */