package com.example.carrentalapp.usecase

import com.example.carrentalapp.entities.SpeedLimit
import com.example.carrentalapp.data.interfaces.SpeedLimitRepository
import com.example.carrentalapp.entities.Renter

/**
 * Use case class for fetching the speed limit of a specific car based on the renter's details.
 * @param speedLimitRepository The repository used to fetch speed limit data.
 */
class GetSpeedLimitUseCase(private val speedLimitRepository: SpeedLimitRepository) {
    /**
     * Executes the use case to fetch the speed limit for a given car and renter.
     *
     * @param carId The unique identifier of the car for which the speed limit is being fetched.
     * @param customer The renter's details, used to determine the applicable speed limit.
     * @return The `SpeedLimit` object if available, or `null` if no speed limit is found.
     */
    fun execute(carId: String, customer: Renter): SpeedLimit? {
        return speedLimitRepository.getSpeedLimit(carId, customer)
    }
}