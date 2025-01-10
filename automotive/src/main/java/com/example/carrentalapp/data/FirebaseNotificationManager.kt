package com.example.carrentalapp.data

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.carrentalapp.data.interfaces.NotificationRepository
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging

class FirebaseNotificationManager  {
    private val database = FirebaseDatabase.getInstance()

     fun sendNotification(message: String, channel: String) {
      //send notification to fleet
    }
}
