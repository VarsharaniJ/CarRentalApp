package com.example.carrentalapp.data

import com.example.carrentalapp.data.interfaces.SpeedLimitRepository
import com.example.carrentalapp.entities.Renter
import com.example.carrentalapp.entities.SpeedLimit

class SpeedLimitRepositoryImpl(private val apiService: ApiService) : SpeedLimitRepository {
    override fun getSpeedLimit(carId: String, renter: Renter): SpeedLimit? {
        return apiService.getSpeedLimit(carId, renter)
    }
}