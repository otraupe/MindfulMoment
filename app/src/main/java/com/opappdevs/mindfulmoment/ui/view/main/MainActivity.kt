package com.opappdevs.mindfulmoment.ui.view.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()   // shows the splash screen them until the activity is fully loaded
                                // ...then switches to the main theme (FavorItTheme defined for the
                                // ...composable functions)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
                Main()
            }
        }
    }
}