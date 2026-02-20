package com.opappdevs.mindfulmoment.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.opappdevs.mindfulmoment.data.prefs.BooleanPreferences.NOTIFICATIONS_ENABLED
import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import com.opappdevs.mindfulmoment.domain.notification.AlarmScheduler
import com.opappdevs.mindfulmoment.worker.NotificationWorker
import timber.log.Timber
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DefaultAlarmSchedulerImpl @Inject constructor(
    private val context: Context,
    private val _preferencesManager: PreferencesManager
) : AlarmScheduler {

    private val _alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private val _workManager = WorkManager.getInstance(context)

    override fun scheduleForNotification(totalMinutesOfTheDay: Int, type: NotificationService.NotificationType) {
        if (totalMinutesOfTheDay < 0) {
            Timber.d("Notification time not set, not scheduling alarm.")
            return
        }
        if (!_preferencesManager.get(NOTIFICATIONS_ENABLED)) {
            Timber.d("Notifications disabled in the app, not scheduling alarm.")
            return
        }

        val intent = Intent(context, NotificationAlarmReceiver::class.java).apply {
            // Add the notification type's ID as an extra,
            // so the receiver knows which notification to show
            putExtra(NOTIFICATION_TYPE_EXTRA, type.notificationId)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            type.notificationId, // Use the notification type's ID for a unique PendingIntent
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        //now convert the total minutes into correctly zoned milliseconds since the epoch
        // Convert total minutes into a LocalTime object.
        val desiredTime = LocalTime.of(
            totalMinutesOfTheDay / 60, // hour
            totalMinutesOfTheDay % 60  // minute
        )
        // Combine with today's date to get a full LocalDateTime.
        var nextTriggerDateTime = LocalDateTime.of(LocalDate.now(), desiredTime)
        // If that time is in the past, schedule it for the same time tomorrow.
        if (nextTriggerDateTime.isBefore(LocalDateTime.now())) {
            nextTriggerDateTime = nextTriggerDateTime.plusDays(1)
        }
        // Convert the final LocalDateTime to epoch milliseconds using the system's current time zone.
        val triggerAtMillis = nextTriggerDateTime.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000

        if (canScheduleExactAlarms()) {
            Timber.d("exact alarm scheduling")
            _alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                triggerAtMillis,
                pendingIntent
            )
        } else {
            Timber.d("inexact alarm scheduling")
            _alarmManager.setAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                triggerAtMillis,
                pendingIntent
            )
        }
        Timber.d("Alarm of type ${type.name} scheduled for: $nextTriggerDateTime")
    }

    override fun cancelForNotification(type: NotificationService.NotificationType) {
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            type.notificationId,
            Intent(context, NotificationAlarmReceiver::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        _alarmManager.cancel(pendingIntent)
        Timber.d("Alarm of type ${type.name} cancelled.")
    }

    override fun enqueueDailyAlarmWorker() {
        // Create an IMMEDIATE, one-time work request to ensure the alarm
        // for the current day is rescheduled as soon as possible after a reboot.
        val initialWorkRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .build()

        // Enqueue the one-time work. ExistingWorkPolicy.KEEP ensures it doesn't
        // get scheduled multiple times if the receiver is triggered again.
        _workManager.enqueueUniqueWork(
            uniqueWorkName = NotificationWorker.ONETIME_WORK_NAME,
            existingWorkPolicy = ExistingWorkPolicy.KEEP,
            request = initialWorkRequest
        )

        // Create periodic work request for daily reminder.
        // Every 12 hours, just to be safe.
        val dailyWorkRequest =
            PeriodicWorkRequestBuilder<NotificationWorker>(
                repeatInterval = 23,
                repeatIntervalTimeUnit = TimeUnit.HOURS
            ).build()

        // Enqueue the periodic work. ExistingWorkPolicy.KEEP ensures it doesn't
        // get scheduled multiple times if the receiver is triggered again.
        _workManager.enqueueUniquePeriodicWork(
            uniqueWorkName = NotificationWorker.PERIODIC_WORK_NAME,
            existingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.UPDATE,
            request = dailyWorkRequest
        )
    }

    override fun canScheduleExactAlarms(): Boolean {
        // On Android 12+, we must have the permission.
        // And below the canScheduleExactAlarms() method does not exist.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            return true // Permission is implicit on older versions.
        }
        return _alarmManager.canScheduleExactAlarms()
    }

    companion object {
        const val NOTIFICATION_TYPE_EXTRA = "notification_type_id"
    }
}
