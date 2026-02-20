package com.opappdevs.mindfulmoment.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.opappdevs.mindfulmoment.R
import com.opappdevs.mindfulmoment.ui.view.main.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

class NotificationService @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    enum class NotificationType(
        val channel: Channel,
        val titleRes: Int,
        val textRes: Int,
        val iconRes: Int,
        val priority: Int,
        val notificationId: Int
    ) {
        DAILY_REMINDER(
            channel = Channel.REMINDER,
            titleRes = R.string.notifications_types_daily_reminder_title,
            textRes = R.string.notifications_types_daily_reminder_text,
            iconRes = R.drawable.ic_stat_heart,
            priority = NotificationCompat.PRIORITY_HIGH,
            notificationId = 1
        ), ;
    }
        private val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        registerChannels()
        removeUnusedChannels()
    }

    private fun registerChannels() {
        Channel.entries.forEach { channel ->
            // Create the notification channel (only necessary for Android 8.0+)
            val notificationChannel = NotificationChannel(
                /* id = */ channel.channelId,
                /* name = */ context.getString(channel.channelNameRes),
                /* importance = */ channel.importance
            ).apply {
                description = context.getString(channel.descriptionRes)
            }
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun removeUnusedChannels() {
        // Get a set of all valid channel IDs
        val validChannelIds = Channel.entries.map { it.channelId }.toSet()
        // Get all channels currently registered with the system
        val existingChannels = notificationManager.notificationChannels
        // Filter the existing channels for the ones that are no longer in use
        val channelsToDelete = existingChannels.filter { it.id !in validChannelIds }
        // Delete each obsolete channel
        channelsToDelete.forEach { channel ->
            Timber.d("Deleting obsolete notification channel: ${channel.id} - ${channel.name}")
            notificationManager.deleteNotificationChannel(channel.id)
        }
    }

    fun showNotification(type: NotificationType) {
        Timber.d("Showing notification of type ${type.name}")
        // Create an intent to launch the app when the notification is tapped
        val mainActivityIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
        } //NEW_TASK: required to start activity from outside the app
        //SINGLE_TOP: allows an existing activity to be brought to the foreground
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context, 0, mainActivityIntent, PendingIntent.FLAG_IMMUTABLE
        ) //FLAG_IMMUTABLE: The creator of the PendingIntent can always update the PendingIntent
        // itself via FLAG_UPDATE_CURRENT

        // Build the notification
        val builder = NotificationCompat.Builder(context, type.channel.channelId)
            .setSmallIcon(type.iconRes) // You'll need a notification icon
            .setContentTitle(context.getString(type.titleRes))
            .setContentText(context.getString(type.textRes))
            .setPriority(type.priority)
            .setContentIntent(pendingIntent) // Set the action to perform on tap
            .setAutoCancel(true) // Dismiss the notification when tapped

        // Show the notification
        notificationManager.notify(type.notificationId, builder.build())
        Timber.d("Notification of type ${type.name} shown.")
    }

    companion object {
        enum class Channel(
            val channelId: String,
            val channelNameRes: Int,
            val importance: Int,
            val descriptionRes: Int,
        ) {
            REMINDER(
                channelId = "mindful_moment_daily_reminder",
                channelNameRes = R.string.notifications_channels_reminder_name,
                importance = NotificationManager.IMPORTANCE_HIGH,
                descriptionRes = R.string.notifications_channels_reminder_description,
            ),;
        }
    }
}
