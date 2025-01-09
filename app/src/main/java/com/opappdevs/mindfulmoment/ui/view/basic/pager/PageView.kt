package com.opappdevs.mindfulmoment.ui.view.basic.pager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.opappdevs.mindfulmoment.annotations.ThemePreviews
import com.opappdevs.mindfulmoment.ui.theme.MindfulMomentTheme

@Composable
fun PageView(
    title: String
) {
    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(.85f) //TODO: dimens
                .fillMaxHeight(.75f),
                shape = RoundedCornerShape(20.dp), //TODO: define default shape
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text( //TODO: set-up app's text properties
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp), //TODO: dimens
                    textAlign = TextAlign.Center,
                    text = title
                )
            }
        }
    }
}

@Composable
@ThemePreviews
fun PreviewPageView() {
    MindfulMomentTheme() {
        PageView("Lorem Ipsum")
    }
}