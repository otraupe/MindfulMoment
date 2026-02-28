package com.opappdevs.mindfulmoment.ui.view.main.legal

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.ui.view.base.text.HtmlViewer

@Composable
fun PrivacyPolicy(navController: NavHostController) {
    HtmlViewer(
        stringRes = R.string.ui_legal_privacy_policy,
        textAlign = TextAlign.Justify,
    ) {
        navController.popBackStack()
    }
}