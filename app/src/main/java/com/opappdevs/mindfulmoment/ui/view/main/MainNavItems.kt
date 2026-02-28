package com.opappdevs.mindfulmoment.ui.view.main

import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.navigation.Destinations

enum class MainNavItems(
    val route: String,
    val iconRes: Int,
    val iconCdRes: Int,
    val labelRes: Int
) {
    HOME(
        route = Destinations.Home.route,
        iconRes = R.drawable.ic_home_24px_rounded,
        iconCdRes = R.string.ui_main_navigation_icon_home_cd,
        labelRes = R.string.ui_main_navigation_destination_home
    ),
    IMPRINT(
        route = Destinations.Imprint.route,
        iconRes = R.drawable.ic_mail_24px_rounded,
        iconCdRes = R.string.ui_main_navigation_icon_imprint_cd,
        labelRes = R.string.ui_legal_imprint_title
    ),
    PRIVACY_POLICY(
        route = Destinations.PrivacyPolicy.route,
        iconRes = R.drawable.ic_policy_24px_rounded,
        iconCdRes = R.string.ui_main_navigation_icon_privacy_cd,
        labelRes = R.string.ui_legal_privacy_title
    ),;
}