package com.example.carrentalapp.data

import com.example.carrentalapp.data.interfaces.NotificationRepository

/**
 * Implementation of the [NotificationRepository] interface that sends notifications using
 * [FirebaseNotificationManager].
 *
 * @property firebaseNotificationManager The [FirebaseNotificationManager] instance used
 * to send notifications.
 */
class NotificationRepositoryImpl(private val firebaseNotificationManager: FirebaseNotificationManager?) : NotificationRepository {
    /**
     * Sends a notification with the provided message and channel.
     *
     * This method delegates the task of sending the notification to [firebaseNotificationManager].
     *
     * @param message The message to be sent in the notification.
     * @param channel The channel through which the notification will be sent
     */
    override fun sendNotification(message: String, channel: String) {
        firebaseNotificationManager?.sendNotification(message, channel)
    }
}