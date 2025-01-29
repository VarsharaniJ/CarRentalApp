package com.example.carrentalapp.services

import android.content.Context
import com.example.carrentalapp.entities.Car
import android.car.hardware.property.CarPropertyManager
import android.car.VehiclePropertyIds
import android.util.Log

/**
 * Singleton object for accessing and managing vehicle speed data using the AAOS (Android Automotive OS) API.
 *
 * This object provides methods to initialize, monitor, and release resources for vehicle speed updates.
 */
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


    /**
     * Initializes the provider and registers a callback for vehicle speed updates.
     *
     * This method sets up a connection to the car property manager and starts listening for
     * vehicle speed changes.
     *
     * @param onSpeedUpdate A callback function to handle speed updates. The speed is provided in km/h.
     */
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
        //Releases resources and unregisters callbacks
        carPropertyManager?.unregisterCallback { }
        car?.disconnect()
    }
}