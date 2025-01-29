package com.example.carrentalapp.entities

/**
 * Data class representing a car in the car rental system.
 *
 * This class holds information about the car such as its unique identifier and model name.
 *
 * @param id The unique identifier for the car.
 * @param model The model name of the car.
 */
data class Car( val id: String,
                val model: String)
