package com.example.carrentalapp.usecase

import com.example.carrentalapp.data.interfaces.NotificationRepository

/**
 * Use case class for sending notifications when the speed limit is exceeded.
 */
class NotifySpeedLimitExceededUseCase(private val notificationRepository: NotificationRepository) {
    /**
     * Executes the use case to send a notification when the speed limit is exceeded.
     */
    fun execute(message: String) {
        notificationRepository.sendNotification(message)
    }
}