package com.example.carrentalapp.usecase

import com.example.carrentalapp.entities.SpeedLimit
import com.example.carrentalapp.data.interfaces.SpeedLimitRepository
import com.example.carrentalapp.entities.Renter


class GetSpeedLimitUseCase(private val speedLimitRepository: SpeedLimitRepository) {
    fun execute(carId: String, customer: Renter): SpeedLimit? {
        return speedLimitRepository.getSpeedLimit(carId, customer)
    }
}