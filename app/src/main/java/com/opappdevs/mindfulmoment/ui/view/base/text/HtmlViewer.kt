package com.opappdevs.mindfulmoment.ui.view.base.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.ui.util.fadingEdgeBrush
import com.opappdevs.mindfulmoment.ui.view.base.MindfulCard
import com.opappdevs.mindfulmoment.ui.view.base.button.icon.icons.MindfulIconButtonBack

@Composable
fun HtmlViewer(
    stringRes: Int,
    textAlign: TextAlign,
    onBack: () -> Unit
) {
    val gradientHeight = dimensionResource(R.dimen.mindful_scrollable_text_bottom_gradient_height)
    val fadingEdgeGradient = fadingEdgeBrush()

    MindfulCard(modifier = Modifier.padding(bottom = 16.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            MindfulIconButtonBack { onBack() }
            Box(
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.mindful_base_card_padding)
                )
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .background(color = MaterialTheme.colorScheme.surface)
                ) {
                    Text(
                        text = AnnotatedString.fromHtml(
                            stringResource(stringRes)
                        ),
                        textAlign = textAlign,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontSize = 18.sp,
                            lineHeight = 21.sp,
                            hyphens = Hyphens.Auto, //enable hyphenation
                            lineBreak = LineBreak.Paragraph //higher-quality hyphenation to reduce gaps
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(gradientHeight)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(gradientHeight)
                        .align(Alignment.BottomCenter)
                        .background(fadingEdgeGradient)
                )
            }
        }
    }
}