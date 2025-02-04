package com.example.carrentalapp.data


import com.example.carrentalapp.data.interfaces.NotificationChannel

/**
 * This class encapsulates the logic to send notifications to a fleet using Firebase.
 */
class FirebaseNotificationChannel : NotificationChannel {
    override fun sendNotification(message: String) {
        //send notification to fleet
        // Logic for sending the notification via Firebase will go here
        println("Firebase: $message")
    }
}
