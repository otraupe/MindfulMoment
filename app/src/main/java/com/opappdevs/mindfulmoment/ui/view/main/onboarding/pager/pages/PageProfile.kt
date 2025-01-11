package com.opappdevs.mindfulmoment.ui.view.main.onboarding.pager.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulTextButton

@Composable
fun PageProfile() {
    Column() {
        Text("Möchtest du an deinen täglichen Achtsamkeitsmoment erinnert werden?")
        MindfulTextButton("Gerne") {
        /*TODO: notification request*/
        }
    }
}

//TODO: warum diese Angaben (ausklappbar)

@ThemePreviews
@Composable
fun PreviewPageProfile() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        PageNotifications()
    }
}