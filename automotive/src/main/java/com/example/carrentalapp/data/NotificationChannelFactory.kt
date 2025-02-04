package com.example.carrentalapp.data

import com.example.carrentalapp.data.interfaces.NotificationChannel

class NotificationChannelFactory {
    fun getChannel(): NotificationChannel {
        return if (isFirebaseAvailable()) {
            FirebaseNotificationChannel()
        } else {
            AWSNotificationChannel()
        }
    }

    private fun isFirebaseAvailable(): Boolean {
        // Logic to check Firebase availability
        return true // Replace with actual availability check
    }
}