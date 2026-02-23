package com.opappdevs.mindfulmoment.ui.view.base.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.ui.view.base.MindfulCard
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulButton
import com.opappdevs.mindfulmoment.ui.view.base.button.MindfulTextButton

@Composable
fun MindfulStringPickerDialog(
    stringPickerState: PickerState,
    stringItems: List<String>,
    startIndex: Int,
    titleRes: Int?,
    confirmButtonTextRes: Int,
    dismissButtonTextRes: Int,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    onDismissRequest: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = true
        )
    ) {
        MindfulCard(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            isDialog = true
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (titleRes != null) {
                    Text(stringResource(titleRes))
                }
                Picker(
                    items = stringItems,
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .padding(vertical = dimensionResource(R.dimen.mindful_base_card_padding)),
                    state = stringPickerState,
                    startIndex = startIndex,
                    visibleItemsCount = 3,
                    textModifier = Modifier,
                    textStyle = LocalTextStyle.current.copy(fontSize = 48.sp),
                    dividerColor = MaterialTheme.colorScheme.primary
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    MindfulTextButton(
                        labelRes = dismissButtonTextRes,
                    ) {
                        onDismiss()
                    }
                    MindfulButton(
                        labelRes = confirmButtonTextRes,
                    ) {
                        onConfirm()
                    }
                }
            }
        }
    }
}