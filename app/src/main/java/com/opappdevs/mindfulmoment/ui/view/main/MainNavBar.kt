package com.opappdevs.mindfulmoment.ui.view.main

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.opappdevs.mindfulmoment.navigation.BottomNavItem

/**
 * The bottom nav bar visible for the views of the main activity.
 * */
//TODO: we only need this for the home Destination
//@Composable
//fun MainNavBar(navController: NavController, bottomNavItems: Set<BottomNavItem>) {
//    NavigationBar {
//        val backStackEntry = navController.currentBackStackEntryAsState()
//        bottomNavItems.forEach { item ->
//            val selected = item.route == backStackEntry.value?.destination?.route   // item is selected
//                                                            // if topmost backstack entry points to it
//            NavigationBarItem(
//                selected = selected,
//                onClick = { navController.navigate(item.route) },   // prevent navigating to current item
//                label = {
//                    Text(
//                        text = item.title,
//                        fontWeight = FontWeight.SemiBold,
//                    )
//                },
//                icon = {
//                    Icon(
//                        imageVector = ImageVector.vectorResource(item.icon),
//                        contentDescription = "${item.title} Icon",  // string resource with wildcard
//                    )
//                }
//            )
//        }
//    }
//}