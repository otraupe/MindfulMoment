package com.opappdevs.mindfulmoment.ui.view.main

import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.navigation.Destination

enum class MainNavItems(
    val route: String,
    val iconRes: Int,
    val iconCdRes: Int,
    val labelRes: Int
) {
    HOME(
        route = Destination.Home.route,
        iconRes = R.drawable.ic_home_24px_rounded,
        iconCdRes = R.string.ui_main_navigation_icon_home_cd,
        labelRes = R.string.ui_main_navigation_destination_home
    ),
    IMPRINT(
        route = Destination.Imprint.route,
        iconRes = R.drawable.ic_mail_24px_rounded,
        iconCdRes = R.string.ui_main_navigation_icon_imprint_cd,
        labelRes = R.string.ui_legal_imprint_title
    ),
    PRIVACY_POLICY(
        route = Destination.PrivacyPolicy.route,
        iconRes = R.drawable.ic_policy_24px_rounded,
        iconCdRes = R.string.ui_main_navigation_icon_privacy_cd,
        labelRes = R.string.ui_legal_privacy_title
    ),;

    companion object {
        fun getItemFromRoute(route: String?) =
            entries.firstOrNull { it.route == route }
    }
}