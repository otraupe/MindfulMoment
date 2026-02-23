package com.opappdevs.mindfulmoment.ui.view.base.text

import android.text.SpannableString
import android.text.style.URLSpan
import android.text.util.Linkify
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.core.text.util.LinkifyCompat

/**
 * A composable that behaves like Android's Linkify. It finds links (web, email) in the text
 * and makes them clickable, opening them in the appropriate app.
 *
 * @param text The text to be displayed.
 * @param modifier The modifier to be applied to the composable.
 */
@Composable
fun LinkifyText(
    text: String,
    modifier: Modifier = Modifier
) {
    val linkColor = MaterialTheme.colorScheme.tertiary

    val uriHandler = LocalUriHandler.current

    val annotatedString = remember(text) {
        // 1. Create a SpannableString and run LinkifyCompat on it.
        val spannable = SpannableString(text)
        LinkifyCompat.addLinks(spannable, Linkify.WEB_URLS or Linkify.EMAIL_ADDRESSES)

        // 2. Find all the URLSpans that LinkifyCompat added.
        val spans = spannable.getSpans(0, spannable.length, URLSpan::class.java)

        // 3. Build the AnnotatedString, applying styles and annotations for each span.
        buildAnnotatedString {
            append(text)
            spans.forEach { span ->
                val start = spannable.getSpanStart(span)
                val end = spannable.getSpanEnd(span)

                // Apply a style to make the link look clickable.
                addStyle(
                    style = SpanStyle(
                        color = linkColor,
                        textDecoration = TextDecoration.None
                    ),
                    start = start,
                    end = end
                )
                // Add an annotation to the link range, storing the URL.
                addStringAnnotation(
                    tag = "URL",
                    annotation = span.url,
                    start = start,
                    end = end
                )
            }
        }
    }

    // 4. Use ClickableText to handle clicks on the annotated string
    ClickableText(
        text = annotatedString,
        style = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onSurface // Ensure default text color is correct
        ),
        modifier = modifier,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    uriHandler.openUri(annotation.item)
                }
        }
    )
}