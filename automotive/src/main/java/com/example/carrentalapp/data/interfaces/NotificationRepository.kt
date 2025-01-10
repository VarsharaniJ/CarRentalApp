package com.example.carrentalapp.data.interfaces

interface NotificationRepository {
    fun sendNotification(message: String, channel: String)
}