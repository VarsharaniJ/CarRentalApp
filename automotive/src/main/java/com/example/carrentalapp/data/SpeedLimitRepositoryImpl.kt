package com.example.carrentalapp.data

import com.example.carrentalapp.data.interfaces.SpeedLimitRepository
import com.example.carrentalapp.entities.Renter
import com.example.carrentalapp.entities.SpeedLimit

/**
 * Implementation of the [SpeedLimitRepository] interface that interacts with an API service
 * to retrieve speed limit information for a given car and renter.
 *
 * @property apiService The API service used to fetch the speed limit data.
 */
class SpeedLimitRepositoryImpl(private val apiService: ApiService) : SpeedLimitRepository {
    /**
     * Retrieves the speed limit for a specific car and renter.
     *
     * @param carId The unique identifier of the car.
     * @param renter The renter for whom the speed limit is being fetched.
     * @return The [SpeedLimit] object containing the car's speed limit, or null if the data couldn't be retrieved.
     */
    override fun getSpeedLimit(carId: String, renter: Renter): SpeedLimit? {
        return apiService.getSpeedLimit(carId, renter)
    }
}