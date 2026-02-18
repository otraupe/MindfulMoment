package com.opappdevs.mindfulmoment.ui.view.main.legal

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.ui.view.base.MindfulBackground

@Composable
fun Imprint(
    navController: NavHostController,
    snackState: SnackbarHostState
) {
    MindfulBackground(
        background = {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.bg_heart_on_grass),
                contentDescription = stringResource(R.string.ui_onboarding_background_cd),
                contentScale = ContentScale.Crop
            )
        }
    ) {}
}