package com.opappdevs.mindfulmoment.ext

import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AlignmentSpan
import java.net.URI

fun String.centered(): Spannable {              // Spannable allows for formatted strings
    val centeredText: Spannable = SpannableString(this)
    centeredText.setSpan(
        AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
        0, this.length - 1,                     // set span on full length
        Spannable.SPAN_INCLUSIVE_INCLUSIVE      // pre-/appended strings are centered as well
    )
    return centeredText
}