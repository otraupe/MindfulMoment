package com.opappdevs.mindfulmoment.ui.view.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.navigation.Destinations
import com.opappdevs.mindfulmoment.navigation.DoubleBackToExit
import com.opappdevs.mindfulmoment.navigation.NavGraph
import com.opappdevs.mindfulmoment.ui.view.base.MindfulBackground
import kotlinx.coroutines.launch

@Composable
fun Main(
    isOnboardingComplete: Boolean = false
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }    // will not survive config changes
    val scope = rememberCoroutineScope()    // for basic UI transformations...
    // such as opening the nav drawer; coroutines will be canceled
    // if the calling composable leaves the composition

    DoubleBackToExit(navController = navController, scope = scope)
    // close the drawer on back gesture
    BackHandler(enabled = drawerState.isOpen) {
        scope.launch {
            drawerState.close()
        }
    }
    MindfulBackground(
        modifier = Modifier.fillMaxSize(),
        background = {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.bg_heart_on_grass),
                contentDescription = stringResource(R.string.ui_onboarding_background_cd),
                contentScale = ContentScale.Crop
            )
        }
    ) {
        MainNavDrawer(
            drawerState = drawerState,
            scope = scope,
            gesturesEnabled = (currentRoute(navController) != Destinations.Onboarding.route),
            navController = navController,
        ) {
            Scaffold(
                containerColor = Color.Transparent,
                contentWindowInsets = WindowInsets(0.dp),
                topBar = {
                    if (currentRoute(navController) != Destinations.Onboarding.route) {
                        MainTopBar(drawerState, scope)
                    }
                },
                snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
            ) { contentPadding ->
                NavGraph(
                    navController = navController,
                    snackState = snackBarHostState,
                    startDestination = if (!isOnboardingComplete) {
                        Destinations.Onboarding.route
                    } else {
                        Destinations.Home.route
                    },
                    modifier = Modifier.padding(contentPadding),
                )
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}