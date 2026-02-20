package com.opappdevs.mindfulmoment.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.opappdevs.mindfulmoment.data.prefs.IntPreferences.NOTIFICATION_DAILY_TIME_MINUTES
import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import com.opappdevs.mindfulmoment.domain.notification.AlarmScheduler
import com.opappdevs.mindfulmoment.services.NotificationService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val _alarmScheduler: AlarmScheduler,
    private val _preferencesManager: PreferencesManager
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            Timber.d("NotificationWorker: doWork() started. Scheduling next alarm.")

            // Get notification time from preferences
            val totalMinutesOfTheDay = _preferencesManager.get(NOTIFICATION_DAILY_TIME_MINUTES)

            // Cancel existing alarms
            _alarmScheduler.cancelForNotification(
                type = NotificationService.NotificationType.DAILY_REMINDER
            )

            // Schedule the next alarm
            _alarmScheduler.scheduleForNotification(
                totalMinutesOfTheDay = totalMinutesOfTheDay,
                type = NotificationService.NotificationType.DAILY_REMINDER
            )

            Result.success()
        } catch (e: Exception) {
            Timber.e(e, "NotificationWorker: Failed to do work.")
            Result.failure()
        }
    }

    companion object {
        const val ONETIME_WORK_NAME = "MindfulMomentInitialNotificationWorker"
        const val PERIODIC_WORK_NAME = "MindfulMomentDailyNotificationWorker"
    }
}