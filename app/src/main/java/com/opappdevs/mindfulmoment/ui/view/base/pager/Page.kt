package com.opappdevs.mindfulmoment.ui.view.base.pager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulTextButton

@Composable
fun Page(
    state: Pair<Float, Float>, //alpha, scale
    title: String,
    content: @Composable () -> Unit
) {
    val baseAlpha = remember { .8f }
    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(.9f) //TODO: dimens
                .fillMaxHeight(.75f)
                .alpha(baseAlpha * state.first)
                .scale(state.second),
                shape = RoundedCornerShape(20.dp), //TODO: define default shape
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = title)
                content()
            }
        }
    }
}

@Composable
@ThemePreviews
fun PreviewPageView() {
    MindfulMomentTheme(darkTheme = false, dynamicColor = false) {
        Page(
            state = Pair(.5f, .875f),
            title = "Benachrichtigungen"
        ) {
            Column() {
                Text("Möchtest du an deinen täglichen Achtsamkeitsmoment erinnert werden?")
                MindfulTextButton("Gerne") {
                    /*TODO: notification request*/
                }
            }
        }
    }
}