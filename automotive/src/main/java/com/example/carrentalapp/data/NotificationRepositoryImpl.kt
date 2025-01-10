package com.example.carrentalapp.data

import com.example.carrentalapp.data.interfaces.NotificationRepository

class NotificationRepositoryImpl(private val firebaseNotificationManager: FirebaseNotificationManager?) : NotificationRepository {
    override fun sendNotification(message: String, channel: String) {
        firebaseNotificationManager?.sendNotification(message, channel)
    }
}