package com.example.carrentalapp.entities

/**
 * Data class representing the speed limit information for a specific car.
 *
 * This class holds the car's ID and the maximum speed allowed for that car.
 *
 * @param carId The unique identifier for the car.
 * @param maxSpeed The maximum speed limit (in km/h) for the car.
 */
data class SpeedLimit(  val carId: String,
                        val maxSpeed: Int )
