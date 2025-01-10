package com.example.carrentalapp.services

import com.example.carrentalapp.application.SpeedLimitManager
import com.example.carrentalapp.data.FirebaseNotificationManager

object ServiceComponents {
    var speedLimitManager: SpeedLimitManager? = null
    var firebaseNotificationManager: FirebaseNotificationManager? = null
}