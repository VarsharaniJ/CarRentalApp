package com.example.carrentalapp

import android.app.Application
import com.example.carrentalapp.services.AAOSSpeedProvider

/**
 * This class serves as the entry point of the application and is used to perform
 * app-wide initializations, such as initializing the AAOS speed provider.
 */
class CarRentalApp : Application() {
    /**
     * Initializes the `AAOSSpeedProvider` with the application context to enable speed monitoring
     * functionality throughout the app.
     */
    override fun onCreate() {
        super.onCreate()
        AAOSSpeedProvider.initializeWithContext(this)
    }
}