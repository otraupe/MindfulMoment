package com.opappdevs.mindfulmoment.ext

import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AlignmentSpan
import java.net.URI

/**
 * Extension functions for Strings.
 * */
fun String.centered(): Spannable {              // Spannable allows for formatted strings
    val centeredText: Spannable = SpannableString(this)
    centeredText.setSpan(
        AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
        0, this.length - 1,                     // set span on full length
        Spannable.SPAN_INCLUSIVE_INCLUSIVE      // pre-/appended strings are centered as well
    )
    return centeredText
}

fun String.removeUriParameters(): String {
    return try {
        val uri = URI(this)
        URI(
            uri.scheme,
            uri.authority,
            uri.path,
            null,           // ignore the query part
            null            // ignore the fragment part
        ).toString()
    } catch (t: Throwable) {
        this                    // return original string if not URI conform
    }
}