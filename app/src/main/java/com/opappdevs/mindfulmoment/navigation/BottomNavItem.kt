package com.opappdevs.mindfulmoment.navigation

import com.opappdevs.mindfulmoment.R

/**
 * Defines the available navigation items for the BottomNavigationBar.
* */
// TODO: integrate bottom navigation with horizontal pager
sealed class BottomNavItem(var title: String, var icon: Int, var route: String){
    data object Home : BottomNavItem("Home", R.drawable.ic_home,"home")
}