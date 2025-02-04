package com.example.carrentalapp.data

import com.example.carrentalapp.data.interfaces.NotificationChannel
/**
 * This class encapsulates the logic to send notifications to a fleet using AWS.
 */
class AWSNotificationChannel : NotificationChannel {
    override fun sendNotification(message: String) {
        //send notification to fleet
        // Logic for sending the notification via AWS will go here
        println("AWS: $message")
    }
}