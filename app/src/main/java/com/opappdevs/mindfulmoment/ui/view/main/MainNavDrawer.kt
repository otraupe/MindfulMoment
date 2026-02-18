package com.opappdevs.mindfulmoment.ui.view.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.opappdevs.mindfulmoment.navigation.Destinations
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
//    val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)
//    val selectedItem = remember { mutableStateOf(items[0]) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
//                Spacer(Modifier.height(12.dp))
//                items.forEach { item ->
//                    NavigationDrawerItem(
//                        icon = { Icon(item, contentDescription = null) },
//                        label = { Text(item.name) },
////                        selected = item == selectedItem.value,
//                        selected = false,
//                        onClick = {
//                            scope.launch { drawerState.close() }
////                            selectedItem.value = item
//                        },
//                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
//                    )
//                }
                NavigationDrawerItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Email,
                                contentDescription = null
                            )
                               },
                        label = { Text("Impressum") },
//                        selected = item == selectedItem.value,
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(Destinations.Imprint.route)
//                            selectedItem.value = item
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
            }
        },
        gesturesEnabled = gesturesEnabled,     // allows swiping for open/close and clicking outside to close
        content = content
    )
}