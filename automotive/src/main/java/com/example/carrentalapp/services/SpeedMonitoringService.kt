package com.example.carrentalapp.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder

import androidx.core.app.NotificationCompat

import androidx.lifecycle.MutableLiveData

import com.example.carrentalapp.entities.Renter
/**
 * A foreground service that monitors the speed of a vehicle and notifies if the speed limit is exceeded.
 */
class SpeedMonitoringService : Service() {

    //Stores the ID of the car being monitored
    private var carId: String? = null

    //Stores the details of the renter associated with the car
    private var renter: Renter? = null

    // LiveData to observe the speed changes
    private val _speedLiveData = MutableLiveData<Float>()

    /**
     * Exposes the speed LiveData to allow external observation of speed changes.
     */
    val speedLiveData: MutableLiveData<Float> get() = _speedLiveData

    // Binder instance to provide service reference to the client
    private val binder = LocalBinder()


    override fun onCreate() {
        super.onCreate()

        // Starts the service in the foreground
        startForegroundService()

    }

    /**
     * Starts the service in the foreground with a notification.
     */
    private fun startForegroundService() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "speed_monitoring_channel"
        val channelName = "Speed Monitoring"

        // Create a notification channel for Android O and above
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)

        // Build and show the foreground notification
        val notification: Notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Speed Monitoring")
            .setContentText("Monitoring vehicle speed...")
            .build()

        startForeground(1, notification)
    }

    /**
     * Starts monitoring the speed for a specific car and renter.
     *
     * @param carId The ID of the car to monitor.
     * @param renter The renter associated with the car.
     */
    fun startMonitoring(carId: String, renter: Renter) {
        this.carId = carId
        this.renter = renter

        // Initializes the AAOSSpeedProvider to provide speed updates
        AAOSSpeedProvider.initialize { speed ->
            _speedLiveData.postValue(speed)
            // Check if speed exceeds the limit
            carId.let { id ->
                renter.let { r ->
                    ServiceComponents.speedLimitManager?.checkAndNotify(id, r, speed.toInt())
                }
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }


    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }


    override fun onDestroy() {
        super.onDestroy()
        //Releases resources
        AAOSSpeedProvider.releaseResources()
    }

    /**
     * A local binder class to provide access to the service instance for the client.
     */
    inner class LocalBinder : Binder() {
        fun getService(): SpeedMonitoringService {
            return this@SpeedMonitoringService
        }
    }
}