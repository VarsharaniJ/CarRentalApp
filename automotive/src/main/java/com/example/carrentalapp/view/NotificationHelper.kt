package com.example.carrentalapp.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat

/**
 * A helper class to manage and display notifications.
 */
class NotificationHelper(private val context: Context) {
    // NotificationManager to handle notification operations
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    /**
     * Displays a local notification with the provided message.
     */
    fun showLocalNotification(message: String) {
        val channelId = "SPEED_ALERTS"

        val channel = NotificationChannel(
            channelId,
            "Speed Alerts",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)

        // Build and display the notification
        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Speed Alert")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(1, notification)
    }
}