package com.opappdevs.mindfulmoment.ui.view.main

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.navigation.Destination
import com.opappdevs.mindfulmoment.navigation.NavHelper
import com.opappdevs.mindfulmoment.navigation.navigateIfNew
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * The navigation drawer visible for the views of the main activity - UNDER CONSTRUCTION.
 * */
@Composable
fun MainNavDrawer(
    drawerState: DrawerState,
    scope: CoroutineScope,
    gesturesEnabled: Boolean,
    navController: NavHostController,
    content: @Composable () -> Unit
) {
    navController.currentBackStackEntryAsState()
    val items = MainNavItems.entries
    val selectedItem = remember { mutableStateOf(items[0]) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(dimensionResource(R.dimen.mindful_base_card_sub_spacing)))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                ImageVector.vectorResource(item.iconRes),
                                contentDescription = stringResource(item.iconCdRes)
                            )
                        },
                        label = { Text(stringResource(item.labelRes)) },
                        selected = item == selectedItem.value,
                        onClick = {
                            //home: pop backstack until there
                            if (item == MainNavItems.HOME) {
                                navController.popBackStack(
                                    route = Destination.Home.route,
                                    inclusive = false
                                )
                            } else {
                                //prevent endless backstack
                                val popUpTo = if (
                                    NavHelper.currentRoute(navController) == Destination.Home.route
                                ) null else Destination.Home
                                navController.navigateIfNew(item.route, popUpTo)
                            }
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                        },
                        modifier = Modifier.padding(
                            NavigationDrawerItemDefaults.ItemPadding
                        ),
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        )
                    )
                }
            }
        },
        gesturesEnabled = gesturesEnabled,     // allows swiping for open/close and clicking outside to close
        content = content
    )
}