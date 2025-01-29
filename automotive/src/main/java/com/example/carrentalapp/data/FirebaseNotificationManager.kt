package com.example.carrentalapp.data


import com.google.firebase.database.FirebaseDatabase

/**
 * Manager class responsible for handling the sending of notifications via Firebase.
 *
 * This class encapsulates the logic to send notifications to a fleet using Firebase.
 */
class FirebaseNotificationManager  {

    /**
     * Sends a notification with the given message and channel.
     *
     * This method handles the sending of notifications to the fleet using Firebase.
     * The specifics of the notification sending (such as configuration, formatting, etc.)
     * are handled within this method.
     *
     * @param message The message content that will be included in the notification.
     * @param channel The channel through which the notification will be sent
     */
     fun sendNotification(message: String, channel: String) {
      //send notification to fleet
         // Logic for sending the notification via Firebase will go here

    }
}
