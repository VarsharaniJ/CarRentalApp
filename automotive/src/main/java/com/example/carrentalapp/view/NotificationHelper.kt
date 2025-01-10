package com.example.carrentalapp.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat


class NotificationHelper(private val context: Context) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showLocalNotification(message: String) {
        val channelId = "SPEED_ALERTS"
        val channel = NotificationChannel(
            channelId,
            "Speed Alerts",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Speed Alert")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(1, notification)
    }
}