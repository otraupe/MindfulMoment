package com.opappdevs.mindfulmoment.domain.notification

import com.opappdevs.mindfulmoment.services.NotificationService

interface AlarmScheduler {
    //schedule an alarm for displaying a specific notification
    fun scheduleForNotification(totalMinutesOfTheDay: Int, type: NotificationService.NotificationType)

    //cancel alarms for displaying a specific notification
    fun cancelForNotification(type: NotificationService.NotificationType)

    /**
     * Enqueues the background workers that ensures the daily alarm is
     * always scheduled correctly.
     */
    fun enqueueDailyAlarmWorker()

    /**
     * Checks if the scheduler is capable of setting exact alarms.
     * This abstracts away the Android-specific permission check.
     */
    fun canScheduleExactAlarms(): Boolean
}