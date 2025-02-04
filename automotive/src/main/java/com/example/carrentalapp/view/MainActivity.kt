package com.example.carrentalapp.view

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.example.carrentalapp.application.SpeedLimitManager
import com.example.carrentalapp.data.ApiService
import com.example.carrentalapp.data.NotificationChannelFactory
import com.example.carrentalapp.data.NotificationRepositoryImpl
import com.example.carrentalapp.data.SpeedLimitRepositoryImpl
import com.example.carrentalapp.entities.CustomerType
import com.example.carrentalapp.entities.Renter
import com.example.carrentalapp.services.SpeedMonitoringService
import com.example.carrentalapp.usecase.GetSpeedLimitUseCase
import com.example.carrentalapp.usecase.NotifySpeedLimitExceededUseCase
import com.example.carrentalapp.viewmodel.SpeedMonitoringViewModel

/**
 * The main activity of the car rental app that initializes services, manages the ViewModel,
 * and monitors vehicle speed.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SpeedMonitoringViewModel
    // Instance of the speed monitoring service
    private lateinit var speedMonitoringService: SpeedMonitoringService
    private lateinit var speedLimitManager: SpeedLimitManager
    // Service connection to bind and communicate with the speed monitoring service
    private val serviceConnection = object : ServiceConnection {

        // Called when the service is connected. Initializes the ViewModel and starts speed monitoring.
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as SpeedMonitoringService.LocalBinder
            speedMonitoringService = binder.getService()
            viewModel = SpeedMonitoringViewModel(speedMonitoringService )
            val sampleRenter = Renter("1", "Varsha", "123456789", CustomerType.PREMIUM)

            speedLimitManager = getSpeedLimitManager()
            // Observe speed updates from the service. When a new speed is received,
            // we call checkAndNotify with the updated speed.
            viewModel.speed.observe(this@MainActivity) { speed ->
                println("Current speed: $speed km/h")
                // Use the updated speed value instead of a hardcoded one.
                speedLimitManager.checkAndNotify("1", sampleRenter, speed.toInt())
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            // Handle service disconnection logic
        }
    }

    // Create and return an instance of SpeedLimitManager.
    private fun getSpeedLimitManager(): SpeedLimitManager {
        //Initialize dependencies for the speed limit manager
        val apiService = ApiService()
        val speedLimitRepository = SpeedLimitRepositoryImpl(apiService)
        val getSpeedLimitUseCase = GetSpeedLimitUseCase(speedLimitRepository)
        val channelFactory = NotificationChannelFactory()
        val notificationRepository = NotificationRepositoryImpl(channelFactory)
        val notifySpeedLimitExceededUseCase = NotifySpeedLimitExceededUseCase(notificationRepository)
        val notificationHelper = NotificationHelper(this)
        return  SpeedLimitManager(
            getSpeedLimitUseCase,
            notifySpeedLimitExceededUseCase,
            notificationHelper
        )
    }
    /**
     * Called when the activity is created. Initializes dependencies, services, and bindings.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Start and bind the speed monitoring service
        bindService( Intent(this, SpeedMonitoringService::class.java), serviceConnection, BIND_AUTO_CREATE)

    }

    override fun onDestroy() {
        super.onDestroy()
        // Unbind the service perform cleanup
        unbindService(serviceConnection)
    }
}
