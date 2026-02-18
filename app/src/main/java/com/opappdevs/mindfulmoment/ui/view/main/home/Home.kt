package com.opappdevs.mindfulmoment.ui.view.main.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Home(
    navController: NavHostController,
    snackState: SnackbarHostState
) {
//    val systemUiController = rememberSystemUiController()
//    systemUiController.setSystemBarsColor(
//        color = colorResource(R.color.system_bars_onboarding)
//    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Home",
            fontSize = 72.sp
        )
    }
}