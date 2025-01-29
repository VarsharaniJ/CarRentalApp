package com.example.carrentalapp.services

import com.example.carrentalapp.application.SpeedLimitManager
import com.example.carrentalapp.data.FirebaseNotificationManager

/**
 * Singleton object for managing service-related components in the application.
 *
 * This object provides centralized access to core service components such as
 * the `SpeedLimitManager` and `FirebaseNotificationManager`.
 */
object ServiceComponents {
    /**
     * Instance of `SpeedLimitManager`
     * This is initialized at runtime and can be accessed globally within the app.
     */
    var speedLimitManager: SpeedLimitManager? = null
    /**
     * Instance of `FirebaseNotificationManager`
     * This handles sending and receiving notifications through Firebase.
     */
    var firebaseNotificationManager: FirebaseNotificationManager? = null
}