package com.opappdevs.mindfulmoment.ui.view.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()   // shows the splash screen theme until the activity is fully loaded
                                // ..then switches to the main theme (MindfulMoment theme defined
                                // ..for the composable functions)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
                //TODO: this is not very elegant; wait for a flow?
                Main(viewModel.isOnboardingComplete())
            }
        }
    }
}