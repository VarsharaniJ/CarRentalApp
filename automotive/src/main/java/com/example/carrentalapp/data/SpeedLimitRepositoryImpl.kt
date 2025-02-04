package com.example.carrentalapp.data

import com.example.carrentalapp.data.interfaces.SpeedLimitRepository
import com.example.carrentalapp.entities.Renter
import com.example.carrentalapp.entities.SpeedLimit

/**
 * Implementation of the [SpeedLimitRepository] interface that interacts with an API service
 * to retrieve speed limit information for a given car and renter.
 */
class SpeedLimitRepositoryImpl(private val apiService: ApiService) : SpeedLimitRepository {

    override suspend fun getSpeedLimit(carId: String, renter: Renter): SpeedLimit {
        return apiService.getSpeedLimit(carId, renter)
    }
}