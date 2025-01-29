package com.example.carrentalapp.usecase

import com.example.carrentalapp.data.interfaces.NotificationRepository

/**
 * Use case class for sending notifications when the speed limit is exceeded.
 *
 * @param notificationRepository The repository responsible for sending notifications.
 */
class NotifySpeedLimitExceededUseCase(private val notificationRepository: NotificationRepository) {
    /**
     * Executes the use case to send a notification when the speed limit is exceeded.
     *
     * @param message The notification message to be sent.
     */
    fun execute(message: String) {
        val channel = "Firebase"
        notificationRepository.sendNotification(message, channel)
    }
}