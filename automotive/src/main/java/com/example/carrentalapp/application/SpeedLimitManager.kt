package com.example.carrentalapp.application

import com.example.carrentalapp.entities.Renter
import com.example.carrentalapp.usecase.GetSpeedLimitUseCase
import com.example.carrentalapp.usecase.NotifySpeedLimitExceededUseCase
import com.example.carrentalapp.view.NotificationHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A manager class responsible for checking the speed limit of a car based on the renter's profile
 * and notifying the user if the speed limit is exceeded.
 */
class SpeedLimitManager(
    private val getSpeedLimitUseCase: GetSpeedLimitUseCase,
    private val notifySpeedLimitExceededUseCase: NotifySpeedLimitExceededUseCase,
    private val notificationHelper : NotificationHelper
) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    /**
     * Checks whether the current speed exceeds the speed limit for the given car and renter,
     * and notifies the user if the speed limit is exceeded.
     *
     * This function retrieves the speed limit for the car using the [GetSpeedLimitUseCase] and compares
     * it with the current speed. If the current speed exceeds the limit, it sends a notification using
     * the [NotifySpeedLimitExceededUseCase] and shows a local notification through the [NotificationHelper].
     */
    fun checkAndNotify(carId: String, customer: Renter, currentSpeed: Int) {
        coroutineScope.launch{
            val speedLimit = getSpeedLimitUseCase.execute(carId, customer)
            if (speedLimit != null && currentSpeed > speedLimit.maxSpeed) {
                val message = "Speed limit exceeded! Speed: $currentSpeed km/h (Limit: ${speedLimit.maxSpeed} km/h)"
                notifySpeedLimitExceededUseCase.execute(message)

                // Show local notification for the user
                notificationHelper.showLocalNotification(message)
            }
        }


    }
}