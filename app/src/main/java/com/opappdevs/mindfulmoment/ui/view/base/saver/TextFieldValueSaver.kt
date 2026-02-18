package com.opappdevs.mindfulmoment.ui.view.base.saver

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

/**
 * A custom, robust Saver for [TextFieldValue] that correctly handles saving and restoring
 * its state, preventing crashes that can occur with the default (buggy) Saver.
 *
 * This should be used with rememberSaveable:
 * `rememberSaveable(stateSaver = TextFieldValueSaver) { ... }`
 */
val TextFieldValueSaver: Saver<TextFieldValue, *> = listSaver(
    save = {
        listOf(
            it.text,
            it.selection.start,
            it.selection.end,
            it.composition?.start,
            it.composition?.end
        )
    },
    restore = {
        val text = it[0] as String
        val selection = TextRange(it[1] as Int, it[2] as Int)
        val composition = if (it[3] != null && it[4] != null) {
            TextRange(it[3] as Int, it[4] as Int)
        } else {
            null
        }
        TextFieldValue(text, selection, composition)
    }
)