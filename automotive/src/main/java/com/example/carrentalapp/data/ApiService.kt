package com.example.carrentalapp.data

import com.example.carrentalapp.entities.CustomerType
import com.example.carrentalapp.entities.Renter
import com.example.carrentalapp.entities.SpeedLimit

/**
 * Simulates an API call to fetch the speed limit.
 */
class ApiService {
  suspend  fun getSpeedLimit(carId: String, customer: Renter): SpeedLimit {
        // Logic to fetch speed limit based on customer type
        return when (customer.customerType) {
            CustomerType.PREMIUM -> SpeedLimit(carId, maxSpeed = 120)
            CustomerType.STANDARD -> SpeedLimit(carId, maxSpeed = 100)
        }
    }
}