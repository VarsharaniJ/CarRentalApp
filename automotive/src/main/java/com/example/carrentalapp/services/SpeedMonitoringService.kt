package com.example.carrentalapp.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import com.example.carrentalapp.R
import com.example.carrentalapp.application.SpeedLimitManager
import com.example.carrentalapp.data.FirebaseNotificationManager
import com.example.carrentalapp.entities.Renter

class SpeedMonitoringService : Service() {


    private var carId: String? = null
    private var renter: Renter? = null
    private val _speedLiveData = MutableLiveData<Float>()
    val speedLiveData: MutableLiveData<Float> get() = _speedLiveData


    // Binder to allow communication between Activity and Service
    private val binder = LocalBinder()

    override fun onCreate() {
        super.onCreate()

        val speedLimitManager = ServiceComponents.speedLimitManager

        // Start the foreground service
        startForegroundService()

        // Initialize speed updates
        AAOSSpeedProvider.initialize { speed ->
            _speedLiveData.postValue(speed)
            // Check if speed exceeds the limit
            carId?.let { id ->
                renter?.let { r ->
                    speedLimitManager?.checkAndNotify(id, r, speed.toInt())
                }
            }
        }
    }

    private fun startForegroundService() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "speed_monitoring_channel"
        val channelName = "Speed Monitoring"

        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)

        val notification: Notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Speed Monitoring")
            .setContentText("Monitoring vehicle speed...")
            .build()

        startForeground(1, notification)
    }

    // Function to start monitoring speed
    fun startMonitoring(carId: String, renter: Renter) {
        this.carId = carId
        this.renter = renter
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        AAOSSpeedProvider.releaseResources()
    }



    // LocalBinder class to allow Activity to communicate with the service
    inner class LocalBinder : Binder() {
        fun getService(): SpeedMonitoringService {
            return this@SpeedMonitoringService
        }
    }
}