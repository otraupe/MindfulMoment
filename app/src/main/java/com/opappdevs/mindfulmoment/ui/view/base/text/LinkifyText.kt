package com.opappdevs.mindfulmoment.ui.view.base.text

import android.text.SpannableString
import android.text.style.URLSpan
import android.text.util.Linkify
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
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
    val linkColor = MaterialTheme.colorScheme.primary

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
//                addStyle(
//                    style = SpanStyle(
//                        color = linkColor,
//                        fontWeight = FontWeight.Bold,
//                        textDecoration = TextDecoration.None
//                    ),
//                    start = start,
//                    end = end
//                )
                // Add an annotation to the link range, storing the URL.
                // Use the modern LinkAnnotation
                addLink(
                    url = LinkAnnotation.Url(
                        url = span.url,
                        styles = TextLinkStyles(
                            style = SpanStyle( // You can define styles directly here too
                                color = linkColor,
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.Underline
                            ),
                            focusedStyle = SpanStyle(
                                textDecoration = TextDecoration.Underline
                            ),
                            hoveredStyle = SpanStyle(
                                textDecoration = TextDecoration.Underline
                            ),
                            pressedStyle = SpanStyle(
                                textDecoration = TextDecoration.Underline
                            ),
                        )
                    ),
                    start = start,
                    end = end
                )
            }
        }
    }

    // 4. Use the standard Text composable. It automatically handles LinkAnnotation.
    Text(
        text = annotatedString,
        style = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onSurface // Ensure default text color is correct
        ),
        modifier = modifier
    )
}