package com.opappdevs.mindfulmoment.ext

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date
import java.util.Locale

fun Int.toHourMinuteString(): String {
    if (this < 0) return ""

    val hours = this / 60
    val remainingMinutes = this % 60

    return String.format(Locale.getDefault(),
        "%02d:%02d", hours, remainingMinutes)
}

fun Long.toSimpleDateString(): String {
    if (this < 0) return ""

    val sdf = SimpleDateFormat(
        "dd.MM.yyyy",
        Locale.getDefault()
    )
    return sdf.format(Date(this))
}