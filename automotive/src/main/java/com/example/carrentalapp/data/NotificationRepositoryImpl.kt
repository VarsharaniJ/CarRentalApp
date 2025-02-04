package com.example.carrentalapp.data

import com.example.carrentalapp.data.interfaces.NotificationRepository

/**
 * Implementation of the [NotificationRepository] interface that sends notifications using
 * [FirebaseNotificationChannel] and [AWSNotificationChannel].
 */
class NotificationRepositoryImpl(private val channelFactory: NotificationChannelFactory) : NotificationRepository {
    /**
     * Sends a notification with the provided message and available channel.
     */
    override fun sendNotification(message: String) {
        channelFactory.getChannel().sendNotification(message)
    }
}