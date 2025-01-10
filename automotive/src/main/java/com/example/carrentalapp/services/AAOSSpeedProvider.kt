package com.example.carrentalapp.services

import android.content.Context
import com.example.carrentalapp.entities.Car
import android.car.hardware.property.CarPropertyManager
import android.car.VehiclePropertyIds
import android.util.Log

object  AAOSSpeedProvider {
    private var appContext: Context? = null
    private var car: Car? = null
    private var carPropertyManager: CarPropertyManager? = null
    private var onSpeedUpdate: ((Float) -> Unit)? = null
    private val TAG  = AAOSSpeedProvider::class.toString()

    // Initialize with application context
    fun initializeWithContext(context: Context) {
        appContext = context.applicationContext
    }

    fun initialize(onSpeedUpdate: (Float) -> Unit){
        this.onSpeedUpdate = onSpeedUpdate

        car = Car.createCar(appContext)
        carPropertyManager = car?.getCarManager(Car.PROPERTY_SERVICE) as CarPropertyManager


        carPropertyManager?.registerCallback(
            object : CarPropertyManager.CarPropertyEventCallback {
                override fun onChangeEvent(event: CarPropertyManager.CarPropertyEvent) {
                    if (event.propertyId == VehiclePropertyIds.PERF_VEHICLE_SPEED) {
                        val speed = event.value as Float // Speed in m/s
                        Log.d(TAG,"Vehicle Speed: $speed m/s")
                        onSpeedUpdate(speed * 3.6f) // Convert to km/h
                    }
                }

                override fun onErrorEvent(propId: Int, zone: Int) {
                    Log.e(TAG, "Error reading vehicle speed property")
                }
            },
            VehiclePropertyIds.PERF_VEHICLE_SPEED,
            CarPropertyManager.SENSOR_RATE_NORMAL
        )
    }

    fun releaseResources() {
        carPropertyManager?.unregisterCallback { }
        car?.disconnect()
    }
}