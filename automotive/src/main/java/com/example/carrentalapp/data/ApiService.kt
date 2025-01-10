package com.example.carrentalapp.data

import com.example.carrentalapp.entities.CustomerType
import com.example.carrentalapp.entities.Renter
import com.example.carrentalapp.entities.SpeedLimit

class ApiService {
    // Mocked function to get the speed limit based on customer type
    fun getSpeedLimit(carId: String, customer: Renter): SpeedLimit? {
        // Logic to fetch speed limit based on customer type
        return when (customer.customerType) {
            CustomerType.PREMIUM -> SpeedLimit(carId, maxSpeed = 120) // Premium customers get a higher limit
            CustomerType.STANDARD -> SpeedLimit(carId, maxSpeed = 100) // Standard customers get the default limit
        }
    }
}