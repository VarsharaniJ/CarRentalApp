package com.example.carrentalapp.data.interfaces

import com.example.carrentalapp.entities.Renter
import com.example.carrentalapp.entities.SpeedLimit

interface SpeedLimitRepository {
    fun getSpeedLimit(carId: String, renter : Renter): SpeedLimit?
}