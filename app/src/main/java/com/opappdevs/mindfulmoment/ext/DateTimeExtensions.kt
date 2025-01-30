package com.opappdevs.mindfulmoment.ext

import java.time.LocalDateTime

fun LocalDateTime.toSecondsOfDay(): Int {
    val localTime = this.toLocalTime()
    return localTime.toSecondOfDay()
}