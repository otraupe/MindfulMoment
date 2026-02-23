package com.opappdevs.mindfulmoment.ui.view.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton

@Composable
fun Home(
    navController: NavHostController,
    snackState: SnackbarHostState,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Home",
            fontSize = 72.sp
        )
        MindfulButton(
            labelRes = R.string.ui_main_button_testing_display_daily_notification,
            modifier = Modifier.padding(top = 16.dp),
            onClick = { viewModel.testSendDailyReminder() }
        )
        MindfulButton(
            labelRes = R.string.ui_main_button_testing_cancel_daily_alarm,
            modifier = Modifier.padding(top = 16.dp),
            onClick = { viewModel.testCancelDailyAlarm() }
        )
    }
}