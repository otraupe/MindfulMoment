package com.opappdevs.mindfulmoment.ui.view.main.legal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.ui.view.base.MindfulCard
import com.opappdevs.mindfulmoment.ui.view.base.button.icon.icons.MindfulIconButtonBack
import com.opappdevs.mindfulmoment.ui.view.base.text.LinkifyText

@Composable
fun Imprint_old(navController: NavHostController) {
    MindfulCard(modifier = Modifier.padding(bottom = 16.dp)) {
        Box(contentAlignment = Alignment.TopStart) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Text( //TODO: custom text with auto-scaling
                    text = stringResource(id = R.string.ui_legal_imprint_title),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.mindful_base_card_padding))
                )
                Text( //TODO: typography
                    text = stringResource(id = R.string.ui_legal_imprint_published_by),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.mindful_base_card_padding))
                )
                Text( //TODO: typography
                    text = stringResource(id = R.string.ui_legal_imprint_name_address),
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.mindful_base_text_spacing))
                )
                Text(
                    text = stringResource(id = R.string.ui_legal_imprint_contact),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.mindful_base_card_padding))
                )
                LinkifyText(
                    text = stringResource(id = R.string.ui_legal_imprint_email),
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.mindful_base_text_spacing))
                )
                LinkifyText(
                    text = stringResource(id = R.string.ui_legal_imprint_website),
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.mindful_base_text_spacing))
                )
                Text(
                    text = stringResource(id = R.string.ui_legal_imprint_form_title),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.mindful_base_card_padding))
                )
                Text(
                    text = stringResource(id = R.string.ui_legal_imprint_form_text),
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.mindful_base_text_spacing))
                )
                Text(
                    text = stringResource(id = R.string.ui_legal_imprint_copyright),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = dimensionResource(R.dimen.mindful_base_card_padding))
                        .fillMaxWidth()
                )
            }
            MindfulIconButtonBack { navController.popBackStack() }
        }
    }
}