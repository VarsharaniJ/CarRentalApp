package com.example.carrentalapp.entities

/**
 * This class contains information about the renter such as their ID, name, contact information,
 * and customer type.
 *
 * @param id The unique identifier for the renter.
 * @param name The name of the renter.
 * @param contactInfo The contact information of the renter.
 * @param customerType The type of customer
 */
data class Renter(val id: String,
                  val name: String,
                  val contactInfo: String,
                  val customerType: CustomerType)

