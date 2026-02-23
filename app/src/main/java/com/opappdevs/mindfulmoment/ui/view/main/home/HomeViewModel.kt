package com.opappdevs.mindfulmoment.ui.view.main.home

import androidx.lifecycle.ViewModel
import com.opappdevs.mindfulmoment.domain.notification.AlarmScheduler
import com.opappdevs.mindfulmoment.services.NotificationService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val _notificationService: NotificationService,
    private val _alarmScheduler: AlarmScheduler
): ViewModel() {
    init {
        scheduleAlarmWorker()
    }
    //test show daily reminder notification
    fun testSendDailyReminder() {
        _notificationService.showNotification(
            NotificationService.NotificationType.DAILY_REMINDER
        )
    }
    //test cancel daily alarm
    fun testCancelDailyAlarm() {
        _alarmScheduler.cancelForNotification(
            NotificationService.NotificationType.DAILY_REMINDER
        )
    }

    //start daily alarm worker, e.g. on first app use
    fun scheduleAlarmWorker() {
        _alarmScheduler.enqueueDailyAlarmWorker()
    }
}