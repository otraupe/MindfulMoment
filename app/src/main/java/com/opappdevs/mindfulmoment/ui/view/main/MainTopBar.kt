package com.opappdevs.mindfulmoment.ui.view.main

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.ui.view.base.button.icon.MindfulIconButtonMenu
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * The top bar visible for the views of the main activity.
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(drawerState: DrawerState, scope: CoroutineScope) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))     // visible title
        },
        navigationIcon = {
            MindfulIconButtonMenu {
                when (drawerState.currentValue) {
                    DrawerValue.Closed -> scope.launch { drawerState.open() }
                    DrawerValue.Open -> scope.launch { drawerState.close() }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer    //"on.." color automagically
                                                // picks proper content colors according to light/dark theme
        ),
    )
}