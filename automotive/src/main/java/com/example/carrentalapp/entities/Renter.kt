package com.example.carrentalapp.entities

data class Renter(val id: String,
                  val name: String,
                  val contactInfo: String,
                  val customerType: CustomerType)
enum class CustomerType {
    PREMIUM,
    STANDARD
}
