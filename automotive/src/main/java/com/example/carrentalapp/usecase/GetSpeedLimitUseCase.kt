package com.example.carrentalapp.usecase

import com.example.carrentalapp.entities.SpeedLimit
import com.example.carrentalapp.data.interfaces.SpeedLimitRepository
import com.example.carrentalapp.entities.Renter

/**
 * Use case class for fetching the speed limit of a specific car based on the renter's details.
 */
class GetSpeedLimitUseCase(private val speedLimitRepository: SpeedLimitRepository) {
    suspend fun execute(carId: String, customer: Renter): SpeedLimit? {
        return speedLimitRepository.getSpeedLimit(carId, customer)
    }
}