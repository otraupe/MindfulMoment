package com.opappdevs.mindfulmoment.ui.view.main.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.ui.view.base.MindfulCard
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton

@Composable
fun WelcomeContent(pagerVisible: MutableState<Boolean>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MindfulCard(
            modifier = Modifier.padding(top = 64.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = stringResource(R.string.ui_onboarding_welcome_title),
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                ),
                color = colorResource(R.color.heart_red),
                textAlign = TextAlign.Center
            )
        }
        //TODO: define some default text colors
        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = stringResource(R.string.ui_onboarding_welcome_subtitle),
            style = MaterialTheme.typography.displaySmall.copy(
                color = colorResource(R.color.dark_gray),
                fontWeight = FontWeight.SemiBold
            ),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.weight(1f))
        MindfulButton(
            modifier = Modifier.padding(48.dp),
            string = stringResource(R.string.ui_onboarding_startButton_label),
            onClick = { pagerVisible.value = true }
        )
    }
}