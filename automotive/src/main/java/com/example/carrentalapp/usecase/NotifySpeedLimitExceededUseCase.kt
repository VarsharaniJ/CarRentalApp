package com.example.carrentalapp.usecase

import com.example.carrentalapp.data.interfaces.NotificationRepository

class NotifySpeedLimitExceededUseCase(private val notificationRepository: NotificationRepository) {
    fun execute(message: String) {
        val channel = "Firebase"
        notificationRepository.sendNotification(message, channel)
    }
}