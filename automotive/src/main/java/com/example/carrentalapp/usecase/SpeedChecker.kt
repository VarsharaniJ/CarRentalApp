package com.example.carrentalapp.usecase

import com.example.carrentalapp.data.interfaces.SpeedLimitRepository

class SpeedChecker(private val speedLimitRepository: SpeedLimitRepository) {
    /*fun isSpeedExceeded(carId: String, speed: Int): Boolean {
        val speedLimit = speedLimitRepository.getSpeedLimit(carId)
        return speedLimit != null && speed > speedLimit.maxSpeed
    }*/
}