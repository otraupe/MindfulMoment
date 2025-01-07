package com.opappdevs.mindfulmoment.ui.view.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.opappdevs.mindfulmoment.R
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
            IconButton(         // 'Burger' menu for opening/closing the nav drawer
                onClick = {
                    when (drawerState.currentValue) {
                        DrawerValue.Closed -> scope.launch { drawerState.open() }
                        DrawerValue.Open -> scope.launch { drawerState.close() }
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(id = R.string.ui_main_menu_cd)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer    //"on.." color automagically
                                                // picks proper content colors according to light/dark theme
        ),
    )
}