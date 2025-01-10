package com.example.carrentalapp.application

import com.example.carrentalapp.entities.Renter
import com.example.carrentalapp.usecase.GetSpeedLimitUseCase
import com.example.carrentalapp.usecase.NotifySpeedLimitExceededUseCase
import com.example.carrentalapp.view.NotificationHelper

class SpeedLimitManager(
    private val getSpeedLimitUseCase: GetSpeedLimitUseCase,
    private val notifySpeedLimitExceededUseCase: NotifySpeedLimitExceededUseCase,
    private val notificationHelper : NotificationHelper
) {

    fun checkAndNotify(carId: String, customer: Renter, currentSpeed: Int) {
        val speedLimit = getSpeedLimitUseCase.execute(carId, customer)
        if (speedLimit != null && currentSpeed > speedLimit.maxSpeed) {
            val message = "Speed limit exceeded! Speed: $currentSpeed km/h (Limit: ${speedLimit.maxSpeed} km/h)"
            notifySpeedLimitExceededUseCase.execute(message)

             // Show local notification for the user
            notificationHelper.showLocalNotification(message)
        }
    }
}