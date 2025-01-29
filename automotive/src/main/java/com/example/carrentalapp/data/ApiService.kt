package com.example.carrentalapp.data

import com.example.carrentalapp.entities.CustomerType
import com.example.carrentalapp.entities.Renter
import com.example.carrentalapp.entities.SpeedLimit

/**
 * A service class that provides functionality to interact with APIs
 * for retrieving the speed limit for a specific car and renter.
 *
 * In this mock implementation, the speed limit is determined based on the customer's
 * type (either PREMIUM or STANDARD).
 */
class ApiService {

    /**
     * Fetches the speed limit for a given car and renter based on the renter's customer type.
     *
     * This is a mocked function that simulates an API call to retrieve the speed limit for a car
     * depending on the type of renter (e.g., PREMIUM or STANDARD).
     * @param carId The unique identifier of the car.
     * @param customer The renter who is using the car, containing information like their customer type.
     * @return A [SpeedLimit] object that represents the car's speed limit, or null if the data is not available.
     */
    fun getSpeedLimit(carId: String, customer: Renter): SpeedLimit? {
        // Logic to fetch speed limit based on customer type
        return when (customer.customerType) {
            CustomerType.PREMIUM -> SpeedLimit(carId, maxSpeed = 120)
            CustomerType.STANDARD -> SpeedLimit(carId, maxSpeed = 100)
        }
    }
}