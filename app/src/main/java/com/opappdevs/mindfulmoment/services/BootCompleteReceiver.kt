package com.opappdevs.mindfulmoment.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.opappdevs.mindfulmoment.domain.notification.AlarmScheduler
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class BootCompleteReceiver : BroadcastReceiver() {

    @Inject
    lateinit var alarmScheduler: AlarmScheduler

    override fun onReceive(context: Context, intent: Intent) {
        // Check if broadcast actually is for BOOT_COMPLETED event (just in case)
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            Timber.d("BootCompleteReceiver: Device has booted. Scheduling daily reminder worker.")

            alarmScheduler.enqueueDailyAlarmWorker()
        }
    }
}