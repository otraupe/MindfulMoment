package com.opappdevs.mindfulmoment.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.opappdevs.mindfulmoment.services.DefaultAlarmSchedulerImpl.Companion.NOTIFICATION_TYPE_EXTRA
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class NotificationAlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var notificationService: NotificationService

    override fun onReceive(context: Context, intent: Intent) {
        Timber.d("NotificationAlarmReceiver onReceive")

        // Get the notification type ID from the intent's extras.
        // Provide a default value just in case.
        val typeId = intent.getIntExtra(NOTIFICATION_TYPE_EXTRA, -1)

        // Find the matching NotificationType from the enum.
        val notificationType = NotificationService.NotificationType.entries.find {
            it.notificationId == typeId
        }

        // Show the notification if a valid type was found.
        if (notificationType != null) {
            notificationService.showNotification(notificationType)
            Timber.d("Received alarm and showing notification for type: ${notificationType.name}")
        } else {
            Timber.w("Received alarm with unknown notification type ID: $typeId")
        }
    }
}