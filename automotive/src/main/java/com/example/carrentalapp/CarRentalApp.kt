package com.example.carrentalapp

import android.app.Application
import com.example.carrentalapp.services.AAOSSpeedProvider

class CarRentalApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AAOSSpeedProvider.initializeWithContext(this)
    }
}